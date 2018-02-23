(function(){
'use strict';
var app = angular.module('my-app');
app.service('TargetService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllTargets: loadAllTargets,
                loadTargets: loadTargets,
                getAllTargets: getAllTargets,
                getTarget: getTarget,
                createTarget: createTarget,
                updateTarget: updateTarget,
                removeTarget: removeTarget
            };

            return factory;

            function loadAllTargets() {
                console.log('Fetching all targets');
                var deferred = $q.defer();
                $http.get(urls.TARGET_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all targets');
                            $localStorage.targets = response.data.content;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading targets');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function loadTargets(pageNo, length, search, order) {
                console.log('Fetching  Targets');
                var pageable = {
                  		 page:pageNo, size:length,sort:order,search: search||''
                  		};

                  		var config = {
                  		 params: pageable,
                  		 headers : {'Accept' : 'application/json'}
                  		};
            return     $http.get(urls.TARGET_SERVICE_API,  config)
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
            
            function getAllTargets(){
            	console.log('$localStorage.targets');
                return $localStorage.targets;
            }

            function getTarget(id) {
                console.log('Fetching Target with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.TARGET_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Target with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading user with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createTarget(user) {
                console.log('Creating Target');
                var deferred = $q.defer();
                $http.post(urls.TARGET_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllTargets();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Target : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateTarget(user, id) {
                console.log('Updating Target with id '+id);
                var deferred = $q.defer();
                $http.put(urls.TARGET_SERVICE_API + id, user)
                    .then(
                        function (response) {
                            loadAllTargets();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Target with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeTarget(id) {
                console.log('Removing Target with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.TARGET_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllTargets();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Target with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);
   })();