(function(){
'use strict';
var app = angular.module('my-app');

app.service('MappingInsuranceService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllMappingInsurances: loadAllMappingInsurances,
                loadMappingInsurances: loadMappingInsurances,
                getAllMappingInsurances: getAllMappingInsurances,
                getMappingInsurance: getMappingInsurance,
                createMappingInsurance: createMappingInsurance,
                updateMappingInsurance: updateMappingInsurance,
                removeMappingInsurance: removeMappingInsurance
            };

            return factory;

            function loadAllMappingInsurances() {
                console.log('Fetching all mappingInsurances');
                var deferred = $q.defer();
                $http.get(urls.MAPPING_INSURANCE_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all mappingInsurances');
                            $localStorage.mappingInsurances = response.data.content;
                            
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading mappingInsurances');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function loadMappingInsurances(pageNo, length, search, order, currentScreen) {
                console.log('Fetching  MappingInsurances');
                var pageable = {
                  		 page:pageNo, size:length,sort: order,currentScreen:currentScreen, search: search||''
                  		};

                  		var config = {
                  		 params: pageable,
                  		 headers : {'Accept' : 'application/json'}
                  		};
            return     $http.get(urls.MAPPING_INSURANCE_SERVICE_API,  config)
                    .then(
                        function (response) {
                            console.log('Fetched successfully  mappingInsurances');
                            $localStorage.mappingInsurances = response.data.content;
                         return     response ;
                        },
                        function (errResponse) {
                            console.error('Error while loading mappingInsurances');
                            return   errResponse ;
                        }
                    );
            }
            
            function getAllMappingInsurances(){
            	console.log('$localStorage.mappingInsurances');
                return $localStorage.mappingInsurances;
            }

            function getMappingInsurance(id) {
                console.log('Fetching MappingInsurance with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.MAPPING_INSURANCE_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully MappingInsurance with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading user with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createMappingInsurance(user) {
                console.log('Creating MappingInsurance');
                var deferred = $q.defer();
                $http.post(urls.MAPPING_INSURANCE_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllMappingInsurances();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating MappingInsurance : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateMappingInsurance(user, id) {
                console.log('Updating MappingInsurance with id '+id);
                var deferred = $q.defer();
                $http.put(urls.MAPPING_INSURANCE_SERVICE_API + id, user)
                    .then(
                        function (response) {
                            loadAllMappingInsurances();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating MappingInsurance with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeMappingInsurance(id) {
                console.log('Removing MappingInsurance with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.MAPPING_INSURANCE_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllMappingInsurances();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing MappingInsurance with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);
   })();