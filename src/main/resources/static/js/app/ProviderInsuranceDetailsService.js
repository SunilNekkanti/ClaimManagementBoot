(function(){
'use strict';
var app = angular.module('my-app');
app.service('ProviderInsuranceDetailsService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllProviderInsuranceDetailss: loadAllProviderInsuranceDetailss,
                loadProviderInsuranceDetailss: loadProviderInsuranceDetailss,
                getAllProviderInsuranceDetailss: getAllProviderInsuranceDetailss,
                getProviderInsuranceDetails: getProviderInsuranceDetails,
                createProviderInsuranceDetails: createProviderInsuranceDetails,
                updateProviderInsuranceDetails: updateProviderInsuranceDetails,
                removeProviderInsuranceDetails: removeProviderInsuranceDetails
            };

            return factory;

            function loadAllProviderInsuranceDetailss() {
                console.log('Fetching all providerInsuranceDetailss');
                var deferred = $q.defer();
                $http.get(urls.PROVIDER_INSURANCE_DETAILS_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all providerInsuranceDetailss');
                            $localStorage.providerInsuranceDetailss = response.data.content;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading providerInsuranceDetailss');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function loadProviderInsuranceDetailss(pageNo, length, search, order) {
                console.log('Fetching  ProviderInsuranceDetailss');
                var pageable = {
                  		 page:pageNo, size:length,sort:order,search: search||''
                  		};

                  		var config = {
                  		 params: pageable,
                  		 headers : {'Accept' : 'application/json'}
                  		};
            return     $http.get(urls.PROVIDER_INSURANCE_DETAILS_SERVICE_API,  config)
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
            
            function getAllProviderInsuranceDetailss(){
            	console.log('$localStorage.providerInsuranceDetailss');
                return $localStorage.providerInsuranceDetailss;
            }

            function getProviderInsuranceDetails(id) {
                console.log('Fetching ProviderInsuranceDetails with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.PROVIDER_INSURANCE_DETAILS_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully ProviderInsuranceDetails with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading user with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createProviderInsuranceDetails(user) {
                console.log('Creating ProviderInsuranceDetails');
                var deferred = $q.defer();
                $http.post(urls.PROVIDER_INSURANCE_DETAILS_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllProviderInsuranceDetailss();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating ProviderInsuranceDetails : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateProviderInsuranceDetails(user, id) {
                console.log('Updating ProviderInsuranceDetails with id '+id);
                var deferred = $q.defer();
                $http.put(urls.PROVIDER_INSURANCE_DETAILS_SERVICE_API + id, user)
                    .then(
                        function (response) {
                            loadAllProviderInsuranceDetailss();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating ProviderInsuranceDetails with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeProviderInsuranceDetails(id) {
                console.log('Removing ProviderInsuranceDetails with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.PROVIDER_INSURANCE_DETAILS_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllProviderInsuranceDetailss();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing ProviderInsuranceDetails with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);
   })();