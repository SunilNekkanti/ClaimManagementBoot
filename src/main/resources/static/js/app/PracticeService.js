(function(){
'use strict';
var app = angular.module('my-app');
app.service('PracticeService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllPractices: loadAllPractices,
                loadPractices: loadPractices,
                getAllPractices: getAllPractices,
                getPractice: getPractice,
                createPractice: createPractice,
                updatePractice: updatePractice,
                removePractice: removePractice
            };

            return factory;

            function loadAllPractices() {
                console.log('Fetching all practice');
                var deferred = $q.defer();
                $http.get(urls.PRACTICE_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all practice');
                            $localStorage.practice = response.data.content;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading practice');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function loadPractices(pageNo, length, search, order) {
                console.log('Fetching  Practices');
                var pageable = {
                  		 page:pageNo, size:length,sort:order,search: search||''
                  		};

                  		var config = {
                  		 params: pageable,
                  		 headers : {'Accept' : 'application/json'}
                  		};
            return     $http.get(urls.PRACTICE_SERVICE_API,  config)
                    .then(
                        function (response) {
                            console.log('Fetched successfully  practices');
                         return     response ;
                        },
                        function (errResponse) {
                            console.error('Error while loading practices');
                            return   errResponse ;
                        }
                    );
            }
            
            function getAllPractices(){
            	console.log('$localStorage.practice');
                return $localStorage.practice;
            }

            function getPractice(id) {
                console.log('Fetching Practice with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.PRACTICE_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Practice with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading user with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createPractice(user) {
                console.log('Creating Practice');
                var deferred = $q.defer();
                $http.post(urls.PRACTICE_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllPractices();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Practice : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updatePractice(user, id) {
                console.log('Updating Practice with id '+id);
                var deferred = $q.defer();
                $http.put(urls.PRACTICE_SERVICE_API + id, user)
                    .then(
                        function (response) {
                            loadAllPractices();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Practice with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removePractice(id) {
                console.log('Removing Practice with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.PRACTICE_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllPractices();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Practice with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);
   })();