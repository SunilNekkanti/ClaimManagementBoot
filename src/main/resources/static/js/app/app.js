(function() {
  'use strict';
  var app = angular.module('my-app', ['datatables', 'ui.bootstrap', 'datatables.bootstrap', , 'datatables.buttons', 'datatables.fixedcolumns', 'ui.router', 'ngStorage', 'ngAnimate', 'ngSanitize', 'btorfs.multiselect', 'oc.lazyLoad', 'ui.select']);
  app.constant('urls', {
    BASE: '/ClaimManagement',
    USER_SERVICE_API: '/ClaimManagement/api/user/',
    MEMBERSHIP_SERVICE_API: '/ClaimManagement/api/membership/',
    MEMBERSHIP_FOLLOWUP_SERVICE_API: '/ClaimManagement/api/membershipFollowup/',
    MEMBERSHIP_ACTIVITY_SERVICE_API: '/ClaimManagement/api/membershipActivityMonth/',
    INSURANCE_SERVICE_API: '/ClaimManagement/api/insurance/',
    MAPPING_INSURANCE_SERVICE_API: '/ClaimManagement/api/mappingInsurance/',
    LEAD_SERVICE_API: '/ClaimManagement/api/lead/',
    GENDER_SERVICE_API: '/ClaimManagement/api/gender/',
    HOSPITAL_SERVICE_API: '/ClaimManagement/api/hospital/',
    ROLE_SERVICE_API: '/ClaimManagement/api/role/',
    PRIORITY_SERVICE_API: '/ClaimManagement/api/priority/',
    PRACTICE_SERVICE_API: '/ClaimManagement/api/practice/',
    STATE_SERVICE_API: '/ClaimManagement/api/state/',
    STATUS_SERVICE_API: '/ClaimManagement/api/membershipStatus/',
    REPORTMONTH_SERVICE_API: '/ClaimManagement/api/membershipClaimReportMonth/',
    PAYMENT_YEARS_SERVICE_API: '/ClaimManagement/api/paymentYears/',
    LANGUAGE_SERVICE_API: '/ClaimManagement/api/language/',
    PLANTYPE_SERVICE_API: '/ClaimManagement/api/planType/',
    FILE_TYPE_SERVICE_API: '/ClaimManagement/api/fileType/',
    FILE_SERVICE_API: '/ClaimManagement/api/file/',
    FREQUENCY_TYPE_SERVICE_API: '/ClaimManagement/api/frequencyType/',
    MLR_SERVICE_API: '/ClaimManagement/api/medicalLossRatio/',
    NEW_MLR_SERVICE_API: '/ClaimManagement/api/newMedicalLossRatio/',
    NEW_MLR_SUMMARY_SERVICE_API: '/ClaimManagement/api/newMedicalLossRatioSummary/',
    CLAIM_REPORT_SERVICE_API: '/ClaimManagement/api/membershipClaimsReport/',
    CLAIM_RISK_CATEGORY_SERVICE_API: '/ClaimManagement/api/membershipClaimRiskCategory/',
    HEDIS_REPORT_SERVICE_API: '/ClaimManagement/api/hedisReport/',
    HEDIS_REPORT_MEMBERSHIP_SERVICE_API: '/ClaimManagement/api/membershipHedisMeasure/',
    MEMBERSHIP_FOLLOWUPDETAILS_SERVICE_API: '/ClaimManagement/api/membershipFollowupDetails/',
    MEMBERSHIP_PROBLEM_SERVICE_API: '/ClaimManagement/api/membershipProblem/',
    PLACE_OF_SERVICE_API: '/ClaimManagement/api/placeOfService/',
    CATEGORY_SERVICE_API: '/ClaimManagement/api/riskRecon/',
    ALLOCATION_LEVEL_SERVICE_API: '/ClaimManagement/api/allocationLevel/',
    
    ICD_MEASURE_SERVICE_API: '/ClaimManagement/api/icdMeasure/',
    HEDIS_MEASURE_SERVICE_API: '/ClaimManagement/api/hedisMeasure/',
    HEDIS_MEASURE_RULE_SERVICE_API: '/ClaimManagement/api/hedisMeasureRule/',
    PROBLEM_SERVICE_API: '/ClaimManagement/api/problem/',
    HEDIS_MEASURE_GROUP_SERVICE_API: '/ClaimManagement/api/hedisMeasureGroup/',
    PROVIDER_SERVICE_API: '/ClaimManagement/api/provider/',
    FACILITYTYPE_SERVICE_API: '/ClaimManagement/api/facilityType/',
    EVENT_SERVICE_API: '/ClaimManagement/api/event/',
    EVENT_ASSIGNMENT_SERVICE_API: '/ClaimManagement/api/eventAssignment/',
    LOGIN_USER: '/ClaimManagement/getloginInfo',
    FILE_UPLOADER: '/ClaimManagement/api/fileUpload/fileProcessing.do',
    CONTRACT_FILE_UPLOADER: '/ClaimManagement/api/contractFileUpload/fileProcessing.do',
    CONSENT_FORM_FILE_UPLOADER: '/ClaimManagement/api/fileUpload/consentFormFileProcessing.do',
    COUNTY_SERVICE_API: '/ClaimManagement/api/county/',
    EVENT_FREQUENCY_SERVICE_API: '/ClaimManagement/api/eventFrequency/',
    EVENT_MONTH_SERVICE_API: '/ClaimManagement/api/eventMonth/',
    EVENT_WEEKDAY_SERVICE_API: '/ClaimManagement/api/eventWeekDay/',
    FILE_UPLOADED_SERVICE_API: '/ClaimManagement/api/fileUploaded/',
    EVENT_WEEKNUMBER_SERVICE_API: '/ClaimManagement/api/eventWeekNumber/',
    BESTTIMETOCALL_SERVICE_API: '/ClaimManagement/api/bestTimeToCall/',
    CALCULATE_RISK_SCORE_SERVICE_API: '/ClaimManagement/api/calculateRiskScore/',
    MLR_REPORTING_YEARS_SERVICE_API: '/ClaimManagement/api/newMedicalLossRatio/reportingYears/',
    LEADSTATUS_SERVICE_API: '/ClaimManagement/api/leadStatus/',
    LEAD_FLAG_SERVICE_API: '/ClaimManagement/api/leadMembershipFlag/',
    LEADSTATUS_DETAIL_SERVICE_API: '/ClaimManagement/api/leadStatusDetail/',
    EVENTTYPE_SERVICE_API: '/ClaimManagement/api/eventType/',
    TARGET_SERVICE_API: '/ClaimManagement/api/target/',
    ALLOCATION_LEVEL_SERVICE_API: '/ClaimManagement/api/allocationLevel/',
    ALLOCATION_SERVICE_API: '/ClaimManagement/api/allocation/',
    WEIGHTAGE_SERVICE_API: '/ClaimManagement/api/weightage/',
    WEIGHTAGE_TYPE_SERVICE_API: '/ClaimManagement/api/weightageType/',
    CLAIM_STATUS_DETAIL_SERVICE_API: '/ClaimManagement/api/claimStatusDetail/',
    CLAIM_STATUS_SERVICE_API: '/ClaimManagement/api/claimStatus/'
  });

  app.controller('NavbarController', ['$rootScope', '$scope', '$state', '$stateParams', 'UserService', '$localStorage', '$window', function($rootScope, $scope, $state, $stateParams, UserService, $localStorage, $window) {
    $rootScope.displayNavbar = false;
    $scope.localNavbar = true;
    loginUser();

    function loginUser() {
      if ($localStorage.loginUser === undefined) {

        console.log('About to fetch loginUser');
        UserService
          .loginUser()
          .then(
            function(loginUser) {
              console
                .log('fetched loginUser details successfully');
              $rootScope.displayNavbar = true;
              $rootScope.loginUser = $localStorage.loginUser;
            },
            function(errResponse) {
              callMe('logout');
              $rootScope.displayNavbar = false;
              console
                .error('Error while fetching loginUser');

            });
      } else {
        $rootScope.loginUser = $localStorage.loginUser;
        $rootScope.displayNavbar = true;
      }
    }
    $scope.callMe = function(url, params) {
      if (url === 'logout') {
        $rootScope.displayNavbar = false;
        $rootScope.loginUser = undefined;
        $window.localStorage.clear();


      }
      if (params !== '') {
        $state.go(url, params);

      } else {
        $state.go(url);
      }
    };
  }]);



  app.config(['$ocLazyLoadProvider', '$stateProvider', '$urlRouterProvider',
    function($ocLazyLoadProvider, $stateProvider, $urlRouterProvider) {


      $urlRouterProvider.otherwise('login');

      $ocLazyLoadProvider.config({
        'debug': true,
        'events': true,
        'modules': [{
          serie: true,
          name: 'main',
          files: ['js/app/InsuranceService.js','js/app/ClaimStatusService.js', 'js/app/ProviderService.js','js/app/PriorityService.js', 'js/app/GenderService.js', 'js/app/StateService.js', 'js/app/LanguageService.js', 'js/app/PlanTypeService.js', 'js/app/MedicalLossRatioService.js']
        }, {
          serie: true,
          name: 'login',
          files: ['js/app/UserService.js', 'js/app/InsuranceService.js', 'js/app/ProviderService.js', 'js/app/GenderService.js', 'js/app/StateService.js', 'js/app/LanguageService.js', 'js/app/PlanTypeService.js', 'js/app/MedicalLossRatioService.js']
        }, {
          serie: true,
          name: 'main.user',
          files: ['js/app/RoleService.js', 'js/app/UserController.js' ]
        }, {
          serie: true,
          name: 'main.role',
          files: ['js/app/RoleService.js', 'js/app/ClaimStatusService.js','js/app/ClaimStatusDetailService.js','js/app/PriorityService.js','js/app/RoleController.js']
        }, {
          serie: true,
          name: 'main.lead',
          files: ['js/app/LeadMembershipFlagModalInstanceController.js','js/app/LeadMembershipFlagService.js', 'js/app/FileUploadService.js', 'js/app/BestTimeToCallService.js', 'js/app/LeadStatusService.js', 'js/app/LeadStatusDetailService.js', 'js/app/LeadService.js', 'js/app/LeadController.js']
        }, {
          serie: true,
          name: 'main.leadStatus',
          files: ['js/app/LeadStatusService.js', 'js/app/LeadStatusController.js']
        }, {
          serie: true,
          name: 'main.membership',
          files: ['js/app/ICDMeasureService.js', 'js/app/MembershipProblemService.js', 'js/app/MembershipService.js', 'js/app/MembershipStatusService.js', 'js/app/UserService.js', 'js/app/MembershipController.js']
        }, {
          serie: true,
          name: 'main.provider',
          files: ['js/app/ProviderService.js', 'js/app/RefContractInsuranceService.js', 'js/app/FileUploadService.js', 'js/app/ProviderController.js']
        }, 
        {
        	serie: true,
            name: 'main.priority',
            files: ['js/app/PriorityService.js', 'js/app/PriorityController.js']
        },
        
        {
        	serie: true,
            name: 'main.practice',
            files: ['js/app/PracticeService.js', 'js/app/PracticeController.js']
        },
        {
          serie: true,
          name: 'main.providerArchives',
          files: ['js/app/ProviderService.js', 'js/app/RefContractInsuranceService.js', 'js/app/FileUploadService.js', 'js/app/ProviderController.js']
        }, {
          name: 'main.insurance',
          serie: true,
          files: ['js/app/PlanTypeService.js', 'js/app/FileUploadService.js', 'js/app/InsuranceController.js']
        }, 
        {
            name: 'main.mappingInsurance',
            serie: true,
            files: ['js/app/MappingInsuranceService.js','js/app/PlanTypeService.js', 'js/app/FileUploadService.js', 'js/app/MappingInsuranceController.js']
          },
        {
          name: 'main.insuranceArchives',
          serie: true,
          files: ['js/app/PlanTypeService.js', 'js/app/FileUploadService.js', 'js/app/InsuranceController.js']
        }, {
          name: 'main.cpt',
          serie: true,
          files: ['js/app/CPTMeasureService.js', 'js/app/CPTMeasureController.js']
        }, {
          name: 'main.icd',
          serie: true,
          files: ['js/app/ICDMeasureService.js', 'js/app/ICDMeasureController.js']
        }, {
          name: 'main.riskScore',
          serie: true,
          files: ['js/app/RiskScoreService.js', 'js/app/RiskScoreController.js']
        }, {
          name: 'main.hedis',
          serie: true,
          files: ['js/app/HedisMeasureService.js', 'js/app/HedisMeasureGroupService.js', 'js/app/HedisMeasureController.js']
        }, {
          name: 'main.hedisGroup',
          serie: true,
          files: ['js/app/HedisMeasureGroupService.js', 'js/app/HedisMeasureGroupController.js']
        }, {
          name: 'main.planType',
          serie: true,
          files: ['js/app/PlanTypeService.js', 'js/app/PlanTypeController.js']
        },
        {
        	 name: 'main.target',
             serie: true,
             files: ['js/app/TargetService.js', 'js/app/TargetController.js']
        },
        {
          name: 'main.problem',
          serie: true,
          files: ['js/app/ICDMeasureService.js', 'js/app/ProblemService.js', 'js/app/ProblemController.js']
        }, {
          name: 'main.hedisMeasureRule',
          serie: true,
          files: ['js/app/CPTMeasureService.js', 'js/app/ICDMeasureService.js', 'js/app/ModalInstanceController.js', 'js/app/HedisMeasureRuleService.js', 'js/app/ICDMeasureService.js', 'js/app/ProblemService.js', 'js/app/HedisMeasureService.js', 'js/app/FrequencyTypeService.js', 'js/app/HedisMeasureRuleController.js']
        }, {
          name: 'main.frequencyType',
          serie: true,
          files: ['js/app/FrequencyTypeService.js', 'js/app/FrequencyTypeController.js']
        }, 
        
          {
            name: 'main.weightageType',
            serie: true,
            files: ['js/app/WeightageTypeService.js', 'js/app/WeightageTypeController.js']
          },
          
          {
              name: 'main.weightage',
              serie: true,
              files: ['js/app/WeightageService.js', 'js/app/WeightageController.js','js/app/WeightageTypeService.js']
            },
        {
          name: 'main.fileType',
          serie: true,
          files: ['js/app/FileTypeService.js', 'js/app/FileTypeController.js']
        }, 
        {
        	 name: 'main.claimStatus',
             serie: true,
             files: ['js/app/ClaimStatusService.js', 'js/app/ClaimStatusController.js']
        },
        {
       	     name: 'main.allocationLevel',
             serie: true,
             files: ['js/app/AllocationLevelService.js', 'js/app/AllocationLevelController.js']
        },
       
         {
         	  name: 'main.allocation',
              serie: true,
              files: ['js/app/AllocationLevelService.js','js/app/AllocationService.js', 'js/app/AllocationController.js','js/app/PriorityService.js']
         },
        {
        	 name: 'main.claimStatusDetail',
             serie: true,
             files: ['js/app/ClaimStatusDetailService.js', 'js/app/ClaimStatusDetailController.js']
        },
        {
          name: 'main.roomType',
          serie: true,
          files: ['js/app/PlaceOfServiceService.js', 'js/app/PlaceOfServiceController.js']
        }, 
        {
          name: 'main.hospital',
          serie: true,
          files: ['js/app/HospitalService.js', 'js/app/HospitalController.js']
        }, {
          name: 'main.fileUpload',
          serie: true,
          files: ['js/app/FileTypeService.js', 'js/app/FileService.js', 'js/app/FileUploadService.js', 'js/app/FileController.js']
        }, {
          name: 'main.medicalLossRatio',
          serie: true,
          files: ['js/app/MedicalLossRatioService.js', 'js/app/RiskReconService.js', 'js/app/MedicalLossRatioController.js']
        }, {
          name: 'main.newMedicalLossRatio',
          serie: true,
          files: ['js/app/NewMedicalLossRatioService.js', 'js/app/RiskReconService.js', 'js/app/NewMedicalLossRatioController.js']
        }, {
          name: 'main.membershipActivityMonth',
          serie: true,
          files: ['js/app/MembershipService.js', 'js/app/MembershipActivityMonthController.js']
        }, {
          name: 'main.membershipProblem',
          serie: true,
          files: ['js/app/MembershipService.js', 'js/app/ProblemService.js', 'js/app/MembershipProblemController.js']
        }, {
          name: 'main.membershipClaims',
          serie: true,
          files: ['js/app/MembershipClaimsService.js', 'js/app/RiskReconService.js', 'js/app/MembershipClaimsController.js']
        }, {
          name: 'main.membershipHedis',
          serie: true,
          files: ['js/app/HedisReportModalInstanceController.js', 'js/app/MembershipClaimsService.js', 'js/app/HedisMeasureRuleService.js', 'js/app/MembershipHedisService.js', 'js/app/MembershipHedisMeasureService.js', 'js/app/MembershipFollowupService.js', 'js/app/MembershipHedisController.js']
        }]

      });

      // Now set up the states
      $stateProvider
        .state('login', {
          url: '/',
          templateUrl: 'login',
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('login'); // Resolve promise and load before view
            }]
          }
        })
        .state('main', {
          template: '<ui-view> </ui-view>',
          // Make this state abstract so it can never be
          // loaded directly
          abstract: true,

          // Centralize the config resolution
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main');
            }],
            insurances: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              var InsuranceService = $injector.get("InsuranceService");
              console.log('Load all  Insurances');
              var deferred = $q.defer();
              InsuranceService.loadAllInsurances().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }],
            providers: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all providers');
              var ProviderService = $injector.get("ProviderService");
              var deferred = $q.defer();
              ProviderService.loadAllProviders().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }],
            genders: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all genders');
              var GenderService = $injector.get("GenderService");
              var deferred = $q.defer();
              GenderService.loadAllGenders().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }],
            states: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all states');
              var StateService = $injector.get("StateService");
              var deferred = $q.defer();
              StateService.loadAllStates().then(deferred.resolve, deferred.resolve);
              console.log('deferred.promise' + deferred.promise);
              return deferred.promise;
            }],
            languages: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all languages');
              var LanguageService = $injector.get("LanguageService");
              var deferred = $q.defer();
              LanguageService.loadAllLanguages().then(deferred.resolve, deferred.resolve);
              console.log('deferred.promise' + deferred.promise);
              return deferred.promise;
            }],
            users: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all users');
              var UserService = $injector.get("UserService");
              var deferred = $q.defer();
              UserService.loadAllUsers().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }],
            planTypes: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all users');
              var PlanTypeService = $injector.get("PlanTypeService");
              var deferred = $q.defer();
              PlanTypeService.loadAllPlanTypes().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }],
            reportMonths: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all reportMonths');
              var MedicalLossRatioService = $injector.get("MedicalLossRatioService");
              var deferred = $q.defer();
              MedicalLossRatioService.loadAllReportMonths().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]
          }
        })
        .state('main.home', {
          url: '/home',
          templateUrl: 'home'
        })
        .state('main.user', {
          url: '/user',
          templateUrl: 'partials/list',
          controller: 'UserController',
          controllerAs: 'ctrl',
          resolve: {

          }
        })
        .state('main.user.edit', {
          url: '/',
          templateUrl: 'partials/list',
          controller: 'UserController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'userDisplay': false,
          },
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.user');
            }],
            roles: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              var RoleService = $injector.get("RoleService");
              console.log('Load all  Insurances');
              var deferred = $q.defer();
              RoleService.loadAllRoles().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }],
            languages: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              var LanguageService = $injector.get("LanguageService");
              console.log('Load all  Languages');
              var deferred = $q.defer();
              LanguageService.loadAllLanguages().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }],
            insurances: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              var LanguageService = $injector.get("InsuranceService");
              console.log('Load all  insurances');
              var deferred = $q.defer();
              InsuranceService.loadAllInsurances().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }],
            states: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              var LanguageService = $injector.get("StateService");
              console.log('Load all  states');
              var deferred = $q.defer();
              StateService.loadAllStates().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]
          }
        })
        .state('main.lead', {
          url: '/lead',
          templateUrl: 'partials/lead_list',
          controller: 'LeadController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.lead');
            }],
            statuses: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              var LeadStatusService = $injector.get("LeadStatusService");
              console.log('Load all  statuses');
              var deferred = $q.defer();
              LeadStatusService.loadAllLeadStatuses().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }],
            bestTimeToCalls: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              var BestTimeToCallService = $injector.get("BestTimeToCallService");
              console.log('Load all  bestTimeToCalls');
              var deferred = $q.defer();
              BestTimeToCallService.loadAllBestTimeToCalls().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]
          }
        })
        .state('main.lead.edit', {
          url: '/',
          cache: false,
          templateUrl: 'partials/lead_list',
          controller: 'LeadController',
          controllerAs: 'ctrl',
          params: {
            'eventId': '',
            'leadDisplay': true
          },
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.lead');
            }],
            leadStatusDetails: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              var LeadStatusDetailService = $injector.get("LeadStatusDetailService");
              console.log('Load all  leadStatusDetails');
              var deferred = $q.defer();
              LeadStatusDetailService.loadLeadStatusDetails(0, 20, '', null).then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]
          }
        })
        .state('main.role', {
          url: '/role',
          templateUrl: 'partials/role_list',
          controller: 'RoleController',
          controllerAs: 'ctrl',
          resolve: {
        	  loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
                  return $ocLazyLoad.load('main.role');
                }]
          }
        })
          .state('main.role.edit', {
          url: '/',
          templateUrl: 'partials/role_list',
          controller: 'RoleController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'roleDisplay': false,
          },
          resolve: {
        	  loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
                  return $ocLazyLoad.load('main.role');
                }],
                prioritys: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
                    var PriorityService = $injector.get("PriorityService");
                    console.log('Load all  prioritys');
                    var deferred = $q.defer();
                    PriorityService.loadAllPrioritys().then(deferred.resolve, deferred.resolve);
                    return deferred.promise;
                }],
                claimStatus: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
                    console.log('Load all claimStatuses');
                    var ClaimStatusService = $injector.get("ClaimStatusService");
                    var deferred = $q.defer();
                    ClaimStatusService.loadAllClaimStatuses().then(deferred.resolve, deferred.resolve);
                    return deferred.promise;
                }],
                claimStatusDetails: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
                    console.log('Load all claimStatusDetails');
                    var ClaimStatusDetailService = $injector.get("ClaimStatusDetailService");
                    var deferred = $q.defer();
                    ClaimStatusDetailService.loadAllClaimStatusDetailes().then(deferred.resolve, deferred.resolve);
                    return deferred.promise;
                }]
                
          }
        })
             
        .state('main.facilityType', {
          url: '/facilityType',
          templateUrl: 'partials/facilityType_list',
          controller: 'FacilityTypeController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.lead');
            }],
            facilityTypes: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              var FacilityTypeService = $injector.get("FacilityTypeService");
              console.log('Load all  facilityTypes');
              var deferred = $q.defer();
              FacilityTypeService.loadFacilityTypes(0, 20, '', null).then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]
          }
        }).state('main.eventType', {
          url: '/eventType',
          templateUrl: 'partials/event_list',
          controller: 'EventTypeController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.eventType');
            }],
            eventTypes: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              var EventTypeService = $injector.get("EventTypeService");
              console.log('Load all  eventTypes');
              var deferred = $q.defer();
              EventTypeService.loadEventTypes(0, 20, '', null).then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]
          }
        })
        .state('main.leadStatus', {
          url: '/leadStatus',
          templateUrl: 'partials/leadStatus_list',
          controller: 'LeadStatusController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.leadStatus');
            }],
            leadStatuses: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              var LeadStatusService = $injector.get("LeadStatusService");
              console.log('Load all  leadStatuses');
              var deferred = $q.defer();
              LeadStatusService.loadLeadStatuses(0, 20, '', null).then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]
          }
        })
        .state('main.event', {
          url: '/event',
          templateUrl: 'partials/event_list',
          controller: 'EventController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'eventDisplay': false
          },
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.event');
            }],
            eventTypes: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              var EventTypeService = $injector.get("EventTypeService");
              console.log('Load all  eventTypes');
              var deferred = $q.defer();
              EventTypeService.loadEventTypes(0, 20, '', null).then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]
          }
        })
        .state('main.provider', {
          url: '/provider',
          parent: 'main',
          templateUrl: 'partials/provider_list',
          controller: 'ProviderController',
          controllerAs: 'ctrl',
          data: {
            'currentScreen': 'Active',
            'toScreen': 'Archive',
            'linkToScreen': 'main.providerArchives'
          },
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.provider'); // Resolve promise and load before view
            }]
          }
        })
        .state('main.provider.edit', {
          url: '/',
          templateUrl: 'partials/provider_list',
          controller: 'ProviderController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'providerDisplay': false,
          },
          resolve: {
            refContractinsurances: function($q, RefContractInsuranceService) {
              console.log('Load all RefContractInsuranceService');
              var deferred = $q.defer();
              RefContractInsuranceService.loadAllRefContractInsurances().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }
          }
        }).state('main.providerArchives', {
          url: '/providerArchives',
          parent: 'main',
          templateUrl: 'partials/provider_list',
          controller: 'ProviderController',
          controllerAs: 'ctrl',
          data: {
            'currentScreen': 'Archive',
            'toScreen': 'Active',
            'linkToScreen': 'main.provider'
          },
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.provider'); // Resolve promise and load before view
            }]
          }
        })
        .state('main.providerArchives.edit', {
          url: '/',
          templateUrl: 'partials/provider_list',
          controller: 'ProviderController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'providerDisplay': false,
          },
          resolve: {
            refContractinsurances: function($q, RefContractInsuranceService) {
              console.log('Load all RefContractInsuranceService');
              var deferred = $q.defer();
              RefContractInsuranceService.loadAllRefContractInsurances().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }
          }
        })
        .state('main.membership', {
          url: '/membership',
          templateUrl: 'partials/membership_list',
          controller: 'MembershipController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.membership'); // Resolve promise and load before view
            }],
            icdMeasures: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              var ICDMeasureService = $injector.get("ICDMeasureService");
              console.log('Load all  ICDMeasures');
              var deferred = $q.defer();
              ICDMeasureService.loadAllICDMeasures().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]

          }
        })
        .state('main.membership.edit', {
          url: '/',
          templateUrl: 'partials/membership_list',
          controller: 'MembershipController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'membershipDisplay': false,
          },
          resolve: {
            statuses: function($q, MembershipStatusService) {
              console.log('Load all statuses');
              var deferred = $q.defer();
              MembershipStatusService.loadAllMembershipStatuses().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }
          }
        })
        .state('main.insurance', {
          url: '/insurance',
          templateUrl: 'partials/insurance_list',
          controller: 'InsuranceController',
          controllerAs: 'ctrl',
          data: {
            'currentScreen': 'Active',
            'toScreen': 'Archive',
            'linkToScreen': 'main.insuranceArchives'
          },
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.insurance'); // Resolve promise and load before view
            }]
          }
        })
        
        .state('main.insurance.edit', {
          url: '/',
          templateUrl: 'partials/insurance_list',
          controller: 'InsuranceController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'insuranceDisplay': false,
          },
          resolve: {  }
        })
        
         .state('main.mappingInsurance', {
          url: '/mappingInsurance',
          templateUrl: 'partials/mappingInsurance_list',
          controller: 'MappingInsuranceController',
          controllerAs: 'ctrl',
          data: {
            'currentScreen': 'Active',
            'toScreen': 'Archive',
            'linkToScreen': 'main.mappingInsurance'
          },
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.mappingInsurance'); // Resolve promise and load before view
            }]
          }
        })
        
        .state('main.mappingInsurance.edit', {
          url: '/',
          templateUrl: 'partials/mappingInsurance_list',
          controller: 'MappingInsuranceController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'mappingInsuranceDisplay': false,
          },
          resolve: {  }
        })
        
        .state('main.insuranceArchives', {
          url: '/insuranceArchives',
          templateUrl: 'partials/insurance_list',
          controller: 'InsuranceController',
          controllerAs: 'ctrl',
          data: {
            'currentScreen': 'Archive',
            'toScreen': 'Active',
            'linkToScreen': 'main.insurance'
          },
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.insurance'); // Resolve promise and load before view
            }]
          }
        })
        .state('main.insuranceArchives.edit', {
          url: '/',
          templateUrl: 'partials/insurance_list',
          controller: 'InsuranceController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'insuranceDisplay': false,
          },
          resolve: {}
        })
        .state('main.language', {
          url: '/language',
          templateUrl: 'partials/language_list',
          controller: 'LanguageController',
          controllerAs: 'ctrl',
          resolve: {}
        })
        .state('logout', {
          url: '/logout',
          templateUrl: 'logout'
        })
        .state('main.cpt', {
          url: '/cpt',
          templateUrl: 'partials/cpt_list',
          controller: 'CPTMeasureController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.cpt'); // Resolve promise and load before view
            }]
          }
        })
        .state('main.cpt.edit', {
          url: '/',
          templateUrl: 'partials/cpt_list',
          controller: 'CPTMeasureController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'cptDisplay': false,
          },
          resolve: {}
        })
        .state('main.icd', {
          url: '/icd',
          templateUrl: 'partials/icd_list',
          controller: 'ICDMeasureController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.icd'); // Resolve promise and load before view
            }]
          }
        })
        .state('main.icd.edit', {
          url: '/',
          templateUrl: 'partials/icd_list',
          controller: 'ICDMeasureController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'icdDisplay': false,
          },
          resolve: {}
        })
        .state('main.riskScore', {
          url: '/riskScore',
          templateUrl: 'partials/riskScore_list',
          controller: 'RiskScoreController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.riskScore'); // Resolve promise and load before view
            }],
            paymentYears: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              var RiskScoreService = $injector.get("RiskScoreService");
              console.log('Load all  paymentYears');
              var deferred = $q.defer();
              RiskScoreService.loadAllPaymentYears().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]
          }
        })
        .state('main.hedis', {
          url: '/hedis',
          templateUrl: 'partials/hedis_list',
          controller: 'HedisMeasureController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.hedis'); // Resolve promise and load before view
            }]
          }
        })
        .state('main.hedis.edit', {
          url: '/',
          templateUrl: 'partials/hedis_list',
          controller: 'HedisMeasureController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'hedisDisplay': false,
          },
          resolve: {
            hedisGroups: function($q, HedisMeasureGroupService) {
              console.log('Load all states');
              var deferred = $q.defer();
              HedisMeasureGroupService.loadAllHedisMeasureGroups().then(deferred.resolve, deferred.resolve);
              console.log('deferred.promise' + deferred.promise);
              return deferred.promise;
            }
          }
        })
        .state('main.hedisGroup', {
          url: '/hedisGroup',
          templateUrl: 'partials/hedisGroup_list',
          controller: 'HedisMeasureGroupController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.hedisGroup'); // Resolve promise and load before view
            }]
          }
        })
        .state('main.hedisGroup.edit', {
          url: '/',
          templateUrl: 'partials/hedisGroup_list',
          controller: 'HedisMeasureGroupController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'hedisGroupDisplay': false,
          },
          resolve: {}
        })
        .state('main.planType', {
          url: '/planType',
          templateUrl: 'partials/planType_list',
          controller: 'PlanTypeController',
          controllerAs: 'ctrl',
          resolve: {          }
        })
        .state('main.planType.edit', {
          url: '/',
          templateUrl: 'partials/planType_list',
          controller: 'PlanTypeController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'planTypeDisplay': false,
          },
          resolve: {}
        })
        .state('main.problem', {
          url: '/problem',
          templateUrl: 'partials/problem_list',
          controller: 'ProblemController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.problem'); // Resolve promise and load before view
            }],
            icdMeasures: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all ICDMeasures');
              var ICDMeasureService = $injector.get("ICDMeasureService");
              var deferred = $q.defer();
              ICDMeasureService.loadICDMeasures().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]
          }
        })
        .state('main.problem.edit', {
          url: '/',
          templateUrl: 'partials/problem_list',
          controller: 'ProblemController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'problemDisplay': false,
          },
          resolve: {}
        })
        .state('main.hedisMeasureRule', {
          url: '/hedisMeasureRule',
          templateUrl: 'partials/hedisMeasureRule_list',
          controller: 'HedisMeasureRuleController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.hedisMeasureRule'); // Resolve promise and load before view
            }],
            hedisMeasures: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all hedisMeasures');
              var HedisMeasureService = $injector.get("HedisMeasureService");
              var deferred = $q.defer();
              HedisMeasureService.loadAllHedisMeasures().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]
          }
        })
        .state('main.hedisMeasureRule.edit', {
          url: '/',
          templateUrl: 'partials/hedisMeasureRule_list',
          controller: 'HedisMeasureRuleController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'hedisMeasureRuleDisplay': false,
          },
          resolve: {
            icdMeasures: function($q, ICDMeasureService) {
              console.log('Load all ICDMeasures');
              var deferred = $q.defer();
              ICDMeasureService.loadICDMeasures().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            },
            problems: function($q, ProblemService) {
              console.log('Load all problems');
              var deferred = $q.defer();
              ProblemService.loadAllProblems().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            },
            frequencyTypes: function($q, FrequencyTypeService) {
              console.log('Load all frequencyTypes');
              var deferred = $q.defer();
              FrequencyTypeService.loadAllFrequencyTypes().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }
          }
        })
        .state('main.frequencyType', {
          url: '/frequencyType',
          templateUrl: 'partials/frequencyType_list',
          controller: 'FrequencyTypeController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.frequencyType'); // Resolve promise and load before view
            }]
          }
        })
        .state('main.frequencyType.edit', {
          url: '/',
          templateUrl: 'partials/frequencyType_list',
          controller: 'FrequencyTypeController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'frequencyTypeDisplay': false,
          },
          resolve: {}
        })
        .state('main.fileType', {
          url: '/fileType',
          templateUrl: 'partials/fileType_list',
          controller: 'FileTypeController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.fileType'); // Resolve promise and load before view
            }]
          }
        })
        .state('main.fileType.edit', {
          url: '/',
          templateUrl: 'partials/fileType_list',
          controller: 'FileTypeController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'fileTypeDisplay': false,
          },
          resolve: {}
        })
               .state('main.allocationLevel', {
          url: '/allocationLevel',
          templateUrl: 'partials/allocationLevel_list',
          controller: 'AllocationLevelController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.allocationLevel'); // Resolve promise and load before view
            }]
          }
        })
        .state('main.allocationLevel.edit', {
          url: '/',
          templateUrl: 'partials/allocationLevel_list',
          controller: 'AllocationLevelController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'allocationLevelDisplay': false,
          },
          resolve: {}
        })
             .state('main.claimStatus', {
          url: '/claimStatus',
          templateUrl: 'partials/claimStatus_list',
          controller: 'ClaimStatusController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.claimStatus'); // Resolve promise and load before view
            }]
          }
        })
        .state('main.claimStatus.edit', {
          url: '/',
          templateUrl: 'partials/claimStatus_list',
          controller: 'ClaimStatusController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'claimStatusDisplay': false,
          },
          resolve: {}
        })
        .state('main.claimStatusDetail', {
          url: '/claimStatusDetail',
          templateUrl: 'partials/claimStatusDetail_list',
          controller: 'ClaimStatusDetailController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.claimStatusDetail'); // Resolve promise and load before view
            }]
          }
        })
        .state('main.claimStatusDetail.edit', {
          url: '/',
          templateUrl: 'partials/claimStatusDetail_list',
          controller: 'ClaimStatusDetailController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'claimStatusDetailDisplay': false,
          },
          resolve: {}
        })
        
           .state('main.allocation', {
          url: '/allocation',
          templateUrl: 'partials/allocation_list',
          controller: 'AllocationController',                                                
          controllerAs: 'ctrl',
          resolve: {
        	  loadMyService: ['$ocLazyLoad',function($ocLazyLoad) {
              return $ocLazyLoad.load('main.allocation'); // Resolve promise and load before view
            }]
          }
        })
        .state('main.allocation.edit', {
          url: '/',
          templateUrl: 'partials/allocation_list',
          controller: 'AllocationController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'allocationDisplay': false,
          },
          resolve: {
        	  loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
                  return $ocLazyLoad.load('main.allocation'); // Resolve promise and load before view
                }],
              prioritys: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
                  var PriorityService = $injector.get("PriorityService");
                  console.log('Load all  prioritys');
                  var deferred = $q.defer();
                  PriorityService.loadAllPrioritys().then(deferred.resolve, deferred.resolve);
                  return deferred.promise;
              }],
              allocationLevels: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
                  var AllocationLevelService = $injector.get("AllocationLevelService");
                  console.log('Load all  allocationLevels');
                  var deferred = $q.defer();
                  AllocationLevelService.loadAllAllocationLeveles().then(deferred.resolve, deferred.resolve);
                  return deferred.promise;
              }]
          }
        })

        .state('main.roomType', {
          url: '/placeOfService',
          templateUrl: 'partials/placeOfService_list',
          controller: 'PlaceOfServiceController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.roomType'); // Resolve promise and load before view
            }]
          }
        })
        .state('main.roomType.edit', {
          url: '/',
          templateUrl: 'partials/placeOfService_list',
          controller: 'PlaceOfServiceController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'placeOfServiceDisplay': false,
          },
          resolve: {

          }
        })
        
           .state('main.target', {
          url: '/target',
          templateUrl: 'partials/target_list',
          controller: 'TargetController',
          controllerAs: 'ctrl',
          resolve: {
              loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load('main.target');
              }]
            
            }
        })
        .state('main.target.edit', {
          url: '/',
          templateUrl: 'partials/target_list',
          controller: 'TargetController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'targetDisplay': false,
          },
          resolve: {
        	  
               }
        })

         .state('main.weightageType', {
          url: '/weightageType',
          templateUrl: 'partials/weightageType_list',
          controller: 'WeightageTypeController',
          controllerAs: 'ctrl',
          resolve: {
              loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load('main.weightageType');
              }]
            
            }
        })
        .state('main.weightageType.edit', {
          url: '/',
          templateUrl: 'partials/weightageType_list',
          controller: 'WeightageTypeController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'weightageTypeDisplay': false,
          },
          resolve: {
        	  
               }
        }) 
        
        .state('main.weightage', {
          url: '/weightage',
          templateUrl: 'partials/weightage_list',
          controller: 'WeightageController',
          controllerAs: 'ctrl',
          resolve: {
              loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load('main.weightage');
              }],
              weightages: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
                  var WeightageTypeService = $injector.get("WeightageTypeService");
                  console.log('Load all  weightageTypes');
                  var deferred = $q.defer();
                  WeightageTypeService.loadAllWeightageTypes().then(deferred.resolve, deferred.resolve);
                  return deferred.promise;
              }]
            
            }
        })
        .state('main.weightage.edit', {
          url: '/',
          templateUrl: 'partials/weightage_list',
          controller: 'WeightageController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'weightageDisplay': false,
          },
          resolve: {
        	  
               }
        })

         .state('main.priority', {
          url: '/priority',
          templateUrl: 'partials/priority_list',
          controller: 'PriorityController',
          controllerAs: 'ctrl',
          resolve: {
              loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load('main.priority');
              }],
              leadStatusDetails: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
                var PriorityService = $injector.get("PriorityService");
                console.log('Load all  roles');
                var deferred = $q.defer();
                PriorityService.loadAllPrioritys().then(deferred.resolve, deferred.resolve);
                return deferred.promise;
              }]
            }
        })
        .state('main.priority.edit', {
          url: '/',
          templateUrl: 'partials/priority_list',
          controller: 'PriorityController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'priorityDisplay': false,
          },
          resolve: {

          }
        })
        
         .state('main.practice', {
          url: '/practice',
          templateUrl: 'partials/practice_list',
          controller: 'PracticeController',
          controllerAs: 'ctrl',
          resolve: {
              loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
                return $ocLazyLoad.load('main.practice');
              
              }],
              practices: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
                  var PracticeService = $injector.get("PracticeService");
                  console.log('Load all  roles');
                  var deferred = $q.defer();
                  PracticeService.loadAllPractices().then(deferred.resolve, deferred.resolve);
                  return deferred.promise;
                }]
            }
        })
        .state('main.practice.edit', {
          url: '/',
          templateUrl: 'partials/practice_list',
          controller: 'PracticeController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'practiceDisplay': false,
          },
          resolve: {

          }
        })
        .state('main.hospital.edit', {
          url: '/',
          templateUrl: 'partials/hospital_list',
          controller: 'HospitalController',
          controllerAs: 'ctrl',
          params: {
            'id': '',
            'hospitalnDisplay': false,
          },
          resolve: {

          }
        })
        .state('main.fileUpload', {
          url: '/fileUpload',
          templateUrl: 'partials/fileUpload_list',
          controller: 'FileController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.fileUpload'); // Resolve promise and load before view
            }],
            fileTypes: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all fileTypes');
              var FileTypeService = $injector.get("FileTypeService");
              var deferred = $q.defer();
              FileTypeService.loadAllFileTypes().then(deferred.resolve, deferred.resolve);
              console.log('deferred.promise' + deferred.promise);
              return deferred.promise;
            }]
          }
        })
        .state('main.newMedicalLossRatio', {
          url: '/newMedicalLossRatio',
          templateUrl: 'partials/newMedicalLossRatio_list',
          controller: 'NewMedicalLossRatioController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.newMedicalLossRatio'); // Resolve promise and load before view
            }],
            reportMonths: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all reportMonths');
              var NewMedicalLossRatioService = $injector.get("NewMedicalLossRatioService");
              var deferred = $q.defer();
              NewMedicalLossRatioService.loadAllReportMonths().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }],
            reportingYears: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all reportingYears');
              var NewMedicalLossRatioService = $injector.get("NewMedicalLossRatioService");
              var deferred = $q.defer();
              NewMedicalLossRatioService.loadAllReportingYears().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]
          }
        })
        .state('main.membershipActivityMonth', {
          url: '/membershipActivityMonth',
          templateUrl: 'partials/membershipActivityMonth_list',
          controller: 'MembershipActivityMonthController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.membershipActivityMonth'); // Resolve promise and load before view
            }]
          }
        })
        .state('main.membershipProblem', {
          url: '/membershipProblem',
          templateUrl: 'partials/membershipProblem_list',
          controller: 'MembershipProblemController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.membershipProblem'); // Resolve promise and load before view
            }],
            problems: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all problems');
              var ProblemService = $injector.get("ProblemService");
              var deferred = $q.defer();
              ProblemService.loadAllProblems().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]
          }
        })
        .state('main.membershipClaims', {
          url: '/membershipClaims',
          templateUrl: 'partials/membershipClaims_list',
          controller: 'MembershipClaimsController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.membershipClaims'); // Resolve promise and load before view
            }],
            categories: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all categories');
              var MembershipClaimsService = $injector.get("MembershipClaimsService");
              var deferred = $q.defer();
              MembershipClaimsService.loadAllRiskCategories().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]
          }
        })
        .state('main.membershipHedis', {
          url: '/membershipHedis',
          templateUrl: 'partials/membershipHedis_list',
          controller: 'MembershipHedisController',
          controllerAs: 'ctrl',
          resolve: {
            loadMyService: ['$ocLazyLoad', function($ocLazyLoad) {
              return $ocLazyLoad.load('main.membershipHedis'); // Resolve promise and load before view
            }],
            categories: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all categories');
              var MembershipClaimsService = $injector.get("MembershipClaimsService");
              var deferred = $q.defer();
              MembershipClaimsService.loadAllRiskCategories().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }],
            hedisMeasureRules: ['loadMyService', '$q', '$injector', function(loadMyService, $q, $injector) {
              console.log('Load all hedisMeasureRules');
              var HedisMeasureRuleService = $injector.get("HedisMeasureRuleService");
              var deferred = $q.defer();
              HedisMeasureRuleService.loadAllHedisMeasureRules().then(deferred.resolve, deferred.resolve);
              return deferred.promise;
            }]
          }
        });

      function run() {
        FastClick.attach(document.body);
      }
    }

  ]);

  // DatePicker -> NgModel
  app.directive('datePicker', function() {
    return {
      require: 'ngModel',
      link: function(scope, element, attr, ngModel) {
        $(element).datetimepicker({
          locale: 'en-us',
          format: 'MM/DD/YYYY HH:mm',
          parseInputDate: function(data) {
            if (data instanceof Date) {
              return moment(data);
            } else {
              return moment(new Date(data));
            }
          },
          minDate: new Date(new Date().setFullYear(new Date().getFullYear() - 1)),
          maxDate: new Date(new Date().setFullYear(new Date().getFullYear() + 1))
        });

        $(element).on("dp.change", function(e) {
          ngModel.$viewValue = moment(e.date).format('MM/DD/YYYY HH:mm');
          ngModel.$commitViewValue();
        });
      }
    };
  });

  //DatePicker -> NgModel
  app.directive('date1Picker', function() {
    return {
      require: 'ngModel',
      link: function(scope, element, attr, ngModel) {
        $(element).datetimepicker({
          locale: 'en-us',
          format: 'MM/DD/YYYY',
          parseInputDate: function(data) {
            if (data instanceof Date) {
              return moment(data);
            } else {
              return moment(new Date(data));
            }
          },
          maxDate: new Date(new Date().setFullYear(new Date().getFullYear() + 3))
        });

        $(element).on("dp.change", function(e) {
          ngModel.$viewValue = moment(e.date).format('MM/DD/YYYY');
          ngModel.$commitViewValue();
        });
      }
    };
  });

 // DatePicker -> NgModel
  app.directive('date2Picker', function() {
    return {
      require: 'ngModel',
      link: function(scope, element, attr, ngModel) {
        $(element).datetimepicker({
          locale: 'en-us',
          format: 'YYYYMM',
          parseInputDate: function(data) {
            if (data instanceof Date) {
              return moment(data);
            } else {
              return moment(new Date(data));
            }
          },
          minDate: new Date(new Date().setFullYear(new Date().getFullYear() - 1)),
          maxDate: new Date(new Date().setFullYear(new Date().getFullYear() + 1))
        });

        $(element).on("dp.change", function(e) {
          ngModel.$viewValue = moment(e.date).format('YYYYMM');
          ngModel.$commitViewValue();
        });
      }
    };
  });

  app.directive('fileModel', ['$parse', function($parse) {
    return {
      restrict: 'A',
      require: "ngModel",
      link: function(scope, element, attrs, ngModel) {

        var model = $parse(attrs.fileModel);
        var isMultiple = attrs.multiple;
        var modelSetter = model.assign;
        var dirty = false;
        element.bind('change', function() {
          var values = [];
          angular.forEach(element[0].files, function(item) {
            var value = {
              // File Name
              name: item.name,
              //File Size
              size: item.size,
              //File URL to view
              url: URL.createObjectURL(item),
              // File Input Value
              _file: item
            };
            values.push(value);
            scope.myForm.$setDirty();
          });
          scope.$apply(function() {
            if (isMultiple) {
              modelSetter(scope, element[0].files);
            } else {
              modelSetter(scope, element[0].files[0]);
            }
          });

        });
      }
    };
  }]);


  app.directive('phoneInput', function($filter, $browser) {
    return {
      require: 'ngModel',
      link: function($scope, $element, $attrs, ngModelCtrl) {
        var listener = function() {
          var value = $element.val().replace(/[^0-9]/g, '');
          $element.val($filter('tel')(value, false));
        };

        // This runs when we update the text field
        ngModelCtrl.$parsers.push(function(viewValue) {
          return viewValue.replace(/[^0-9]/g, '').slice(0, 10);
        });

        // This runs when the model gets updated on the scope directly and keeps our view in sync
        ngModelCtrl.$render = function() {
          $element.val($filter('tel')(ngModelCtrl.$viewValue, false));
        };

        $element.bind('change', listener);
        $element.bind('keydown', function(event) {
          var key = event.keyCode;
          // If the keys include the CTRL, SHIFT, ALT, or META keys, or the arrow keys, do nothing.
          // This lets us support copy and paste too
          if (key == 91 || (15 < key && key < 19) || (37 <= key && key <= 40)) {
            return;
          }
          $browser.defer(listener); // Have to do this or changes don't get picked up properly
        });

        $element.bind('paste cut', function() {
          $browser.defer(listener);
        });
      }

    };
  });

  app.filter('nonAdminUsersFilter', function() {
    return function(input, arrayOfString) {
      var out = [];
      input.filter(function(user) {
        if (arrayOfString.indexOf(user.role.role) > -1) {

          out.push(user);
        }
      });
      return out;
    };
  });

  app.filter('filterFromArray', function() {
    return function(input, arrayToMatch) {
      input = input || [];
      arrayToMatch = arrayToMatch || [];
      var l = input.length,
        k = arrayToMatch.length,
        out = [],
        i = 0,
        map = {};

      for (; i < k; i++) {
        map[arrayToMatch[k]] = true;
      }

      for (i = 0; i < l; i++) {
        if (map[input[i].name]) {
          out.push(input[i]);
        }
      }

      return out;
    };
  });

  app.filter('filterNotFromArray', function() {
    return function(input, arrayToMatch) {
      input = input || [];
      arrayToMatch = arrayToMatch || [];
      var l = input.length,
        k = arrayToMatch.length,
        out = [],
        i = 0,
        map = {};

      for (; i < k; i++) {
        map[arrayToMatch[k]] = true;
      }

      for (i = 0; i < l; i++) {
        if (!map[input[i].name]) {
          out.push(input[i]);
        }
      }

      return out;
    };
  });

  app.filter('tel', function() {
    return function(tel) {
      if (!tel) {
        return '';
      }

      var value = tel.toString().trim().replace(/^\+/, '');

      if (value.match(/[^0-9]/)) {
        return tel;
      }

      var country, city, number;

      switch (value.length) {
        case 1:
        case 2:
        case 3:
          city = value;
          break;

        default:
          city = value.slice(0, 3);
          number = value.slice(3);
      }

      if (number) {
        if (number.length > 3) {
          number = number.slice(0, 3) + '-' + number.slice(3, 7);
        } else {
          number = number;
        }

        return ("(" + city + ") " + number).trim();
      } else {
        return "(" + city;
      }

    };
  });

  app.filter('range', function() {
    return function(input, total) {
      total = parseInt(total);

      for (var i = 0; i < total; i++) {
        input.push(i);
      }

      return input;
    };
  });

  app.filter('providerFilter', function() {
    return function(prvdrs, insId) {
      var filteredObject = [];
      prvdrs.forEach(function(prvdr) {
        var filteredRefContractObject = [];
        prvdr.prvdrRefContracts.forEach(function(refContract) {
          if (refContract.ins !== null && refContract.ins.id === insId) {
            filteredObject.push(prvdr);
          }
        });
      });
      return filteredObject;
    };
  });

  app.filter('sumByKey', function() {
    return function(data, key) {
      if (typeof(data) === 'undefined' || typeof(key) === 'undefined') {
        return 0;
      }

      var sum = 0;
      angular.forEach(data, function(obj, objKey) {
        sum += parseFloat(obj[key]);
      });

      return sum;
    };
  });

})();
