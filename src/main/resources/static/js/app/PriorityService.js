(function(){
'use strict';
var app = angular.module('my-app');
app.service('PriorityService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllPrioritys: loadAllPrioritys,
                loadPrioritys: loadPrioritys,
                getAllPrioritys: getAllPrioritys,
                getPriority: getPriority,
                createPriority: createPriority,
                updatePriority: updatePriority,
                removePriority: removePriority
            };

            return factory;

            function loadAllPrioritys() {
                console.log('Fetching all priority');
                var deferred = $q.defer();
                $http.get(urls.PRIORITY_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all priority');
                            $localStorage.priority = response.data.content;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading priority');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function loadPrioritys(pageNo, length, search, order) {
                console.log('Fetching  Prioritys');
                var pageable = {
                  		 page:pageNo, size:length,sort:order,search: search||''
                  		};

                  		var config = {
                  		 params: pageable,
                  		 headers : {'Accept' : 'application/json'}
                  		};
            return     $http.get(urls.PRIORITY_SERVICE_API,  config)
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
            
            function getAllPrioritys(){
            	console.log('$localStorage.priority');
                return $localStorage.priority;
            }

            function getPriority(id) {
                console.log('Fetching Priority with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.PRIORITY_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Priority with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading user with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createPriority(user) {
                console.log('Creating Priority');
                var deferred = $q.defer();
                $http.post(urls.PRIORITY_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllPrioritys();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Priority : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updatePriority(user, id) {
                console.log('Updating Priority with id '+id);
                var deferred = $q.defer();
                $http.put(urls.PRIORITY_SERVICE_API + id, user)
                    .then(
                        function (response) {
                            loadAllPrioritys();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Priority with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removePriority(id) {
                console.log('Removing Priority with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.PRIORITY_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllPrioritys();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Priority with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);
   })();