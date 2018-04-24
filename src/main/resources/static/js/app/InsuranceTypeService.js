(function(){
'use strict';
var app = angular.module('my-app');

app.service('InsuranceTypeService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllInsuranceTypes: loadAllInsuranceTypes,
                loadInsuranceTypes: loadInsuranceTypes,
                getAllInsuranceTypes: getAllInsuranceTypes,
                getInsuranceType: getInsuranceType,
                createInsuranceType: createInsuranceType,
                updateInsuranceType: updateInsuranceType,
                removeInsuranceType: removeInsuranceType
            };

            return factory;

            function loadAllInsuranceTypes() {
                console.log('Fetching all insuranceTypes');
                var deferred = $q.defer();
                $http.get(urls.INSURANCE_TYPE_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all insuranceTypes');
                            $localStorage.insuranceTypes = response.data.content;
                            deferred.resolve(response.data.content);
                        },
                        function (errResponse) {
                            console.error('Error while loading insuranceTypes');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function loadInsuranceTypes(pageNo, length, search, order) {
                console.log('Fetching  InsuranceTypes');
                var pageable = {
                  		 page:pageNo, size:length,sort:order,search: search||''
                  		};

                  		var config = {
                  		 params: pageable,
                  		 headers : {'Accept' : 'application/json'}
                  		};
            return     $http.get(urls.INSURANCE_TYPE_SERVICE_API,  config)
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
            
            function getAllInsuranceTypes(){
            	console.log('$localStorage.insuranceTypes');
                return $localStorage.insuranceTypes;
            }

            function getInsuranceType(id) {
                console.log('Fetching InsuranceType with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.INSURANCE_TYPE_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully InsuranceType with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading user with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createInsuranceType(user) {
                console.log('Creating InsuranceType');
                var deferred = $q.defer();
                $http.post(urls.INSURANCE_TYPE_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllInsuranceTypes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating InsuranceType : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateInsuranceType(user, id) {
                console.log('Updating InsuranceType with id '+id);
                var deferred = $q.defer();
                $http.put(urls.INSURANCE_TYPE_SERVICE_API + id, user)
                    .then(
                        function (response) {
                            loadAllInsuranceTypes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating InsuranceType with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeInsuranceType(id) {
                console.log('Removing InsuranceType with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.INSURANCE_TYPE_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllInsuranceTypes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing InsuranceType with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);
   })();