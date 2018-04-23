(function(){
'use strict';
var app = angular.module('my-app');
app.service('ClaimService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllClaims: loadAllClaims,
                loadClaims: loadClaims,
                getAllClaims: getAllClaims,
                getClaim: getClaim,
                createClaim: createClaim,
                updateClaim: updateClaim,
                removeClaim: removeClaim
            };

            return factory;

            function loadAllClaims() {
                console.log('Fetching all claims');
                var deferred = $q.defer();
                $http.get(urls.CLAIM_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all claims');
                            $localStorage.claims = response.data.content;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading claims');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }


            function loadClaims(pageNo, length, search, sortCol , sortDir,teamAssignments,   allocationDate,
            practices,  remarks,  srvcDtFrom,  srvcDtTo,  patientName,  birthDate,	 insurances,
            insuranceTypes,  chargesMin,  chargesMax,  claimStatus,  priorities, userName,  
            ) {
                var pageable = {
                  		 page:pageNo, size:length,sortCol:sortCol, sortDir:sortDir,search: search||'',
                  		 teamAssignments:teamAssignments,allocationDate:allocationDate,practices:practices, remarks:remarks,
              			 srvcDtFrom:srvcDtFrom, srvcDtTo:srvcDtTo, patientName:patientName,  birthDate:birthDate, insurances:insurances,
            			 insuranceTypes:insuranceTypes,  chargesMin:chargesMin,  chargesMax:chargesMax,  claimStatus:claimStatus,  
            			  priorities:priorities, userName:userName
                  		};

                  		var config = {
                  		 params: pageable,
                  		 headers : {'Accept' : 'application/json'}
                  		};
            return     $http.get(urls.CLAIM_SERVICE_API,  config)
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
            
            function getAllClaims(){
            	console.log('$localStorage.claims');
                return $localStorage.claims;
            }

            function getClaim(id) {
                console.log('Fetching Claim with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.CLAIM_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Claim with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading user with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createClaim(user) {
                console.log('Creating Claim');
                var deferred = $q.defer();
                $http.post(urls.CLAIM_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllClaims();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Claim : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateClaim(user, id) {
                console.log('Updating Claim with id '+id);
                var deferred = $q.defer();
                $http.put(urls.CLAIM_SERVICE_API + id, user)
                    .then(
                        function (response) {
                            loadAllClaims();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Claim with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeClaim(id) {
                console.log('Removing Claim with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.CLAIM_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllClaims();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Claim with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);
   })();