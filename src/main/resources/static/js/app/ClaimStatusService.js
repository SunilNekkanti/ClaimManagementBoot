(function(){
'use strict';
var app = angular.module('my-app');

app.service('ClaimStatusService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllClaimStatuses: loadAllClaimStatuses,
                loadClaimStatuses : loadClaimStatuses,
                getAllClaimStatuses: getAllClaimStatuses,
                getClaimStatus: getClaimStatus,
                createClaimStatus: createClaimStatus,
                updateClaimStatus: updateClaimStatus,
                removeClaimStatus: removeClaimStatus
            };

            return factory;

            function loadClaimStatuses(pageNo, length, search, order) {
                console.log('Fetching  ClaimStatuses');
                var deferred = $q.defer();
                var pageable = {
                 		 page:pageNo, size:length,search: search||''
                 		};

                 		var config = {
                 		 params: pageable,
                 		 headers : {'Accept' : 'application/json'}
                 		};
            return     $http.get(urls.CLAIM_STATUS_SERVICE_API, config)
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
            
            function loadAllClaimStatuses() {
                console.log('Fetching all roles');
                var deferred = $q.defer();
                $http.get(urls.CLAIM_STATUS_SERVICE_API)
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

            function getAllClaimStatuses(){
            	console.log('$localStorage.roles');
                return $localStorage.roles;
            }

            function getClaimStatus(id) {
                console.log('Fetching ClaimStatus with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.CLAIM_STATUS_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully ClaimStatus with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading role with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createClaimStatus(role) {
                console.log('Creating ClaimStatus');
                var deferred = $q.defer();
                $http.post(urls.CLAIM_STATUS_SERVICE_API, role)
                    .then(
                        function (response) {
                            loadAllClaimStatuses();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating ClaimStatus : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateClaimStatus(role, id) {
                console.log('Updating ClaimStatus with id '+id);
                var deferred = $q.defer();
                $http.put(urls.CLAIM_STATUS_SERVICE_API + id, role)
                    .then(
                        function (response) {
                            loadAllClaimStatuses();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating ClaimStatus with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeClaimStatus(id) {
                console.log('Removing ClaimStatus with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.CLAIM_STATUS_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllClaimStatuses();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing ClaimStatus with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);
   })();