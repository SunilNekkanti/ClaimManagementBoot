(function(){
'use strict';
var app = angular.module('my-app');

app.service('AllocationService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllAllocationes: loadAllAllocationes,
                loadAllocationes : loadAllocationes,
                getAllAllocationes: getAllAllocationes,
                getAllocation: getAllocation,
                createAllocation: createAllocation,
                updateAllocation: updateAllocation,
                removeAllocation: removeAllocation
            };

            return factory;

            function loadAllocationes(pageNo, length, search, order) {
                console.log('Fetching  Allocationes');
                var deferred = $q.defer();
                var pageable = {
                 		 page:pageNo, size:length,search: search||''
                 		};

                 		var config = {
                 		 params: pageable,
                 		 headers : {'Accept' : 'application/json'}
                 		};
            return     $http.get(urls.ALLOCATION_SERVICE_API, config)
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
            
            function loadAllAllocationes() {
                console.log('Fetching all roles');
                var deferred = $q.defer();
                $http.get(urls.ALLOCATION_SERVICE_API)
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

            function getAllAllocationes(){
            	console.log('$localStorage.roles');
                return $localStorage.roles;
            }

            function getAllocation(id) {
                console.log('Fetching Allocation with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.ALLOCATION_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Allocation with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading role with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createAllocation(role) {
                console.log('Creating Allocation');
                var deferred = $q.defer();
                $http.post(urls.ALLOCATION_SERVICE_API, role)
                    .then(
                        function (response) {
                            loadAllAllocationes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Allocation : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateAllocation(role, id) {
                console.log('Updating Allocation with id '+id);
                var deferred = $q.defer();
                $http.put(urls.ALLOCATION_SERVICE_API + id, role)
                    .then(
                        function (response) {
                            loadAllAllocationes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Allocation with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeAllocation(id) {
                console.log('Removing Allocation with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.ALLOCATION_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllAllocationes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Allocation with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);
   })();