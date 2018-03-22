(function(){
'use strict';
var app = angular.module('my-app');
app.service('WeightageService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllWeightages: loadAllWeightages,
                loadWeightages: loadWeightages,
                getAllWeightages: getAllWeightages,
                getWeightage: getWeightage,
                createWeightage: createWeightage,
                updateWeightage: updateWeightage,
                removeWeightage: removeWeightage
            };

            return factory;

            function loadAllWeightages() {
                console.log('Fetching all weightage');
                var deferred = $q.defer();
                $http.get(urls.WEIGHTAGE_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all weightage');
                            $localStorage.weightage = response.data.content;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading weightage');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function loadWeightages(pageNo, length, search, order) {
                console.log('Fetching  Weightages');
                var pageable = {
                  		 page:pageNo, size:length,sort:order,search: search||''
                  		};

                  		var config = {
                  		 params: pageable,
                  		 headers : {'Accept' : 'application/json'}
                  		};
            return     $http.get(urls.WEIGHTAGE_SERVICE_API,  config)
                    .then(
                        function (response) {
                            console.log('Fetched successfully  providers');
                         return     response ;
                        },
                        function (errResponse) {
                            console.error('Error while loading providers');
                            return   errResponse ;
                        }
                    );
            }
            
            function getAllWeightages(){
            	console.log('$localStorage.weightage');
                return $localStorage.weightage;
            }

            function getWeightage(id) {
                console.log('Fetching Weightage with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.WEIGHTAGE_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Weightage with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading user with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createWeightage(user) {
                console.log('Creating Weightage');
                var deferred = $q.defer();
                $http.post(urls.WEIGHTAGE_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllWeightages();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Weightage : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateWeightage(user, id) {
                console.log('Updating Weightage with id '+id);
                var deferred = $q.defer();
                $http.put(urls.WEIGHTAGE_SERVICE_API + id, user)
                    .then(
                        function (response) {
                            loadAllWeightages();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Weightage with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeWeightage(id) {
                console.log('Removing Weightage with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.WEIGHTAGE_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllWeightages();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Weightage with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);
   })();