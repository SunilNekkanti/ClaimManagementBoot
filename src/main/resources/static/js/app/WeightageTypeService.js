(function(){
'use strict';
var app = angular.module('my-app');
app.service('WeightageTypeService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllWeightageTypes: loadAllWeightageTypes,
                loadWeightageTypes: loadWeightageTypes,
                getAllWeightageTypes: getAllWeightageTypes,
                getWeightageType: getWeightageType,
                createWeightageType: createWeightageType,
                updateWeightageType: updateWeightageType,
                removeWeightageType: removeWeightageType
            };

            return factory;

            function loadAllWeightageTypes() {
                console.log('Fetching all weightageTypes');
                var deferred = $q.defer();
                $http.get(urls.WEIGHTAGE_TYPE_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all weightageTypes');
                            $localStorage.weightageTypes = response.data.content;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading weightageTypes');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function loadWeightageTypes(pageNo, length, search, order) {
                console.log('Fetching  WeightageTypes');
                var pageable = {
                  		 page:pageNo, size:length,sort:order,search: search||''
                  		};

                  		var config = {
                  		 params: pageable,
                  		 headers : {'Accept' : 'application/json'}
                  		};
            return     $http.get(urls.WEIGHTAGE_TYPE_SERVICE_API,  config)
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
            
            function getAllWeightageTypes(){
            	console.log('$localStorage.weightageTypes');
                return $localStorage.weightageTypes;
            }

            function getWeightageType(id) {
                console.log('Fetching WeightageType with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.WEIGHTAGE_TYPE_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully WeightageType with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading user with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createWeightageType(user) {
                console.log('Creating WeightageType');
                var deferred = $q.defer();
                $http.post(urls.WEIGHTAGE_TYPE_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllWeightageTypes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating WeightageType : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateWeightageType(user, id) {
                console.log('Updating WeightageType with id '+id);
                var deferred = $q.defer();
                $http.put(urls.WEIGHTAGE_TYPE_SERVICE_API + id, user)
                    .then(
                        function (response) {
                            loadAllWeightageTypes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating WeightageType with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeWeightageType(id) {
                console.log('Removing WeightageType with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.WEIGHTAGE_TYPE_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllWeightageTypes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing WeightageType with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);
   })();