(function(){
'use strict';
var app = angular.module('my-app');

app.service('AllocationLevelService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllAllocationLeveles: loadAllAllocationLeveles,
                loadAllocationLeveles : loadAllocationLeveles,
                getAllAllocationLeveles: getAllAllocationLeveles,
                getAllocationLevel: getAllocationLevel,
                createAllocationLevel: createAllocationLevel,
                updateAllocationLevel: updateAllocationLevel,
                removeAllocationLevel: removeAllocationLevel
            };

            return factory;

            function loadAllocationLeveles(pageNo, length, search, order) {
                console.log('Fetching  AllocationLeveles');
                var deferred = $q.defer();
                var pageable = {
                 		 page:pageNo, size:length,search: search||''
                 		};

                 		var config = {
                 		 params: pageable,
                 		 headers : {'Accept' : 'application/json'}
                 		};
            return     $http.get(urls.ALLOCATION_LEVEL_SERVICE_API, config)
                    .then(
                        function (response) {
                            console.log('Fetched successfully  roles');
                            $localStorage.roles = response.data.content;
                            deferred.resolve(response);
                         return     response ;
                        },
                        function (errResponse) {
                            console.error('Error while loading roles');
                            deferred.reject(errResponse);
                            return   errResponse ;
                        }
                    );
            }
            
            function loadAllAllocationLeveles() {
                console.log('Fetching all roles');
                var deferred = $q.defer();
                $http.get(urls.ALLOCATION_LEVEL_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all roles');
                            $localStorage.roles = response.data.content;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading roles');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllAllocationLeveles(){
            	console.log('$localStorage.roles');
                return $localStorage.roles;
            }

            function getAllocationLevel(id) {
                console.log('Fetching AllocationLevel with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.ALLOCATION_LEVEL_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully AllocationLevel with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading role with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createAllocationLevel(role) {
                console.log('Creating AllocationLevel');
                var deferred = $q.defer();
                $http.post(urls.ALLOCATION_LEVEL_SERVICE_API, role)
                    .then(
                        function (response) {
                            loadAllAllocationLeveles();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating AllocationLevel : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateAllocationLevel(role, id) {
                console.log('Updating AllocationLevel with id '+id);
                var deferred = $q.defer();
                $http.put(urls.ALLOCATION_LEVEL_SERVICE_API + id, role)
                    .then(
                        function (response) {
                            loadAllAllocationLeveles();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating AllocationLevel with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeAllocationLevel(id) {
                console.log('Removing AllocationLevel with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.ALLOCATION_LEVEL_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllAllocationLeveles();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing AllocationLevel with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);
   })();