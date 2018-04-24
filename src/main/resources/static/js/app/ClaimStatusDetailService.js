(function(){
'use strict';
var app = angular.module('my-app');

app.service('ClaimStatusDetailService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllClaimStatusDetailes: loadAllClaimStatusDetailes,
                loadClaimStatusDetailes : loadClaimStatusDetailes,
                getAllClaimStatusDetailes: getAllClaimStatusDetailes,
                getClaimStatusDetail: getClaimStatusDetail,
                createClaimStatusDetail: createClaimStatusDetail,
                updateClaimStatusDetail: updateClaimStatusDetail,
                removeClaimStatusDetail: removeClaimStatusDetail
            };

            return factory;

            function loadClaimStatusDetailes(pageNo, length, search, order) {
                console.log('Fetching  ClaimStatusDetailes');
                var deferred = $q.defer();
                var pageable = {
                 		 page:pageNo, size:length,search: search||''
                 		};

                 		var config = {
                 		 params: pageable,
                 		 headers : {'Accept' : 'application/json'}
                 		};
            return     $http.get(urls.CLAIM_STATUS_DETAIL_SERVICE_API, config)
                    .then(
                        function (response) {
                            console.log('Fetched successfully  roles');
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
            
            function loadAllClaimStatusDetailes() {
                console.log('Fetching all roles');
                var deferred = $q.defer();
                $http.get(urls.CLAIM_STATUS_DETAIL_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all roles');
                            deferred.resolve(response.data.content);
                        },
                        function (errResponse) {
                            console.error('Error while loading roles');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllClaimStatusDetailes(){
            	console.log('$localStorage.claimStatusDetails');
                return $localStorage.claimStatusDetails;
            }

            function getClaimStatusDetail(id) {
                console.log('Fetching ClaimStatusDetail with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.CLAIM_STATUS_DETAIL_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully ClaimStatusDetail with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading role with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createClaimStatusDetail(role) {
                console.log('Creating ClaimStatusDetail');
                var deferred = $q.defer();
                $http.post(urls.CLAIM_STATUS_DETAIL_SERVICE_API, role)
                    .then(
                        function (response) {
                            loadAllClaimStatusDetailes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating ClaimStatusDetail : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateClaimStatusDetail(role, id) {
                console.log('Updating ClaimStatusDetail with id '+id);
                var deferred = $q.defer();
                $http.put(urls.CLAIM_STATUS_DETAIL_SERVICE_API + id, role)
                    .then(
                        function (response) {
                            loadAllClaimStatusDetailes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating ClaimStatusDetail with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeClaimStatusDetail(id) {
                console.log('Removing ClaimStatusDetail with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.CLAIM_STATUS_DETAIL_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllClaimStatusDetailes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing ClaimStatusDetail with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);
   })();