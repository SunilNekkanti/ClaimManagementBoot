(function() {
  'use strict';
  var app = angular.module('my-app');

  app.controller('ClaimController', ['ClaimService','ProviderService','InsuranceService','ClaimStatusService','ClaimStatusDetailService','ProviderInsuranceDetailsService','PriorityService','PracticeService', '$scope', '$compile', '$state', '$stateParams', 'DTOptionsBuilder', 'DTColumnBuilder', function(ClaimService,ProviderService,InsuranceService,ClaimStatusService,ClaimStatusDetailService,ProviderInsuranceDetailsService,PriorityService,PracticeService,$scope, $compile, $state, $stateParams, DTOptionsBuilder, DTColumnBuilder) {

      var self = this;
      self.claim = {};
      self.claims = [];
      self.prvdrs=[];
      self.insurances=[];
      self.claimStatuss=[];
      self.claimStatusDetails=[];
      self.providerInsuranceDetailss = [];
      self.practices=[];
      self.prioritys = [];
      
      self.display = $stateParams.claimDisplay || false;;
      self.displayEditButton = false;
      self.submit = submit;
      self.getAllClaims = getAllClaims;
      self.getAllProviders = getAllProviders;
      self.getAllInsurances = getAllInsurances;
      self.getAllClaimStatuses = getAllClaimStatuses;
      self.getAllClaimStatusDetailes = getAllClaimStatusDetailes;
      self.getAllProviderInsuranceDetailss = getAllProviderInsuranceDetailss;
      self.getAllPrioritys = getAllPrioritys;
      self.getAllPractices = getAllPractices;
      self.setTeamAssignment = setTeamAssignment;
      
      self.prvdrs = getAllProviders();
      self.insurances = getAllInsurances();
      self.claimStatuss = getAllClaimStatuses();
      self.claimStatusDetails = getAllClaimStatusDetailes();
      self.providerInsuranceDetailss = getAllProviderInsuranceDetailss();
      self.prioritys = getAllPrioritys();
      self.practices = getAllPractices();
      
      self.editClaim = editClaim;
      self.addClaim = addClaim;
      self.dt1Instance = {};
      self.claimId = null;
      self.reset = reset;
      self.claimEdit = claimEdit;
      self.cancelEdit = cancelEdit;
      self.successMessage = '';
      self.errorMessage = '';
      self.done = false;
      self.onlyIntegers = /^\d+$/;
      self.onlyNumbers = /^\d+([,.]\d+)?$/;
      self.checkBoxChange = checkBoxChange;
      self.teamAssignments=0;
      self.allocationDate='';
      self.practices='';
      self.remarks='';
      self.srvcDtFrom='';
      self.srvcDtTo='';
      self.patientName='';
      self.birthDate='';
      self.insurances='';
      self.insuranceTypes='';
      self.chargesMin=0;
      self.chargesMax=9999999;
      self.claimStatus='';
      self.priorities='';
      self.userName='';
      
      if(self.practices != null && self.practices.length > 0){
          self.practice = self.practice || self.practices[0];
       }else  {
          self.practice = {};
       }
      
    
      
      self.dt1Columns = [
        DTColumnBuilder.newColumn('lookup').withTitle('LOOKUP').renderWith(
          function(data, type, full,
            meta) {
            return '<a href="javascript:void(0)" class="' + full.id + '" ng-click="ctrl.claimEdit(' + full.id + ')">' + data + '</a>';
          }).withClass("text-left").withOption("width", '40%'),
        DTColumnBuilder.newColumn('serviceDate').withTitle('SERVICE_DATE').withOption("width", '40%'),
        DTColumnBuilder.newColumn('patient').withTitle('PATIENT_NAME').withOption("width", '400%'),
        DTColumnBuilder.newColumn('dob').withTitle('BIRTH_DATE').withOption("width", '20%'),
        DTColumnBuilder.newColumn('insurance').withTitle('INSURANCE').withOption("width", '20%'),
        DTColumnBuilder.newColumn('insuranceType').withTitle('INSURANCE_TYPE').withOption("width", '20%'),
        DTColumnBuilder.newColumn('charges').withTitle('CHARGES').withOption("width", '20%'),
        DTColumnBuilder.newColumn('statuses').withTitle('STATUS').withOption("width", '20%'),
        DTColumnBuilder.newColumn('priority').withTitle('PRIORITY').withOption("width", '20%')
      
      ];


      self.dt1Options = DTOptionsBuilder.newOptions()
       .withDisplayLength(20)
        .withOption('bServerSide', true)
        .withOption('responsive', true)
        .withOption("bLengthChange", false)
        .withOption("bPaginate", true)
        .withOption('bProcessing', true)
        .withOption('bSaveState', true)
        .withOption('searchDelay', 1000)
        .withOption('createdRow', createdRow)
        .withPaginationType('full_numbers')
        .withOption('ordering', true)
        .withOption('order', [[0, 'ASC']])
        .withOption('aLengthMenu', [[15, 20, -1],[15, 20, "All"]])
        .withOption('bDeferRender', true)
        .withFixedHeader({bottom: true})
        .withLightColumnFilter({
          '0' : { },
          '1' : {  html: 'input',type : 'dateRange' },
          '2' : {  html: 'input',type : 'text' },
          '3' : { html: 'input',type : 'select' },
          '4' : { type : 'text' },
          '5' : { type : 'text' }
        })
        .withFnServerData(serverData);
        
      function serverData(sSource, aoData, fnCallback) {


        // All the parameters you need is in the aoData
        // variable
    	var pracId=(self.practice === undefined || self.practice === null)? 0 : self.practice.id;
        var order = aoData[2].value;
        var page = aoData[3].value / aoData[4].value;
        var length = aoData[4].value;
        var search = aoData[5].value;

        var paramMap = {};
        for (var i = 0; i < aoData.length; i++) {
          paramMap[aoData[i].name] = aoData[i].value;
        }

        var sortCol = '';
        var sortDir = '';
        // extract sort information
        if (paramMap['columns'] !== undefined && paramMap['columns'] !== null && paramMap['order'] !== undefined && paramMap['order'] !== null) {
          sortCol = paramMap['columns'][paramMap['order'][0]['column']].data;
          sortDir = paramMap['order'][0]['dir'];
        }

        // Then just call your service to get the
        // records from server side
            
        ClaimService
          .loadClaims(page, length, search.value, sortCol, sortDir,
          self.teamAssignments,   self.allocationDate,
            self.practices,  self.remarks,  self.srvcDtFrom,  self.srvcDtTo,  self.patientName,  self.birthDate,	self.insurances,
            self.insuranceTypes,  self.chargesMin,  self.chargesMax,  self.claimStatus,  self.priorities, self.userName
            )
          .then(
            function(result) {
              var records = {
                'recordsTotal': result.data.totalElements || 0,
                'recordsFiltered': result.data.totalElements || 0,
                'data': result.data || {}
              };
              fnCallback(records);
              
            });
      }


      function createdRow(row, data, dataIndex) {
        // Recompiling so we can bind Angular directive to the DT
        $compile(angular.element(row).contents())($scope);
      }

      function checkBoxChange(checkStatus, claimId) {
        self.displayEditButton = checkStatus;
        self.claimId = claimId;

      }


      function submit() {
        console.log('Submitting');
        if (self.claim.id === undefined || self.claim.id === null) {
          console.log('Saving New Claim', self.claim);
          createClaim(self.claim);
        } else {
          updateClaim(self.claim, self.claim.id);
          console.log('Claim updated with id ', self.claim.id);
        }
        self.displayEditButton = false;
      }
 



      function getAllClaims() {
        self.claims = ClaimService.getAllClaims();

        return self.claims;
      }
      
      function getAllClaimStatuses(){
      	self.claimStatuses = ClaimStatusService.getAllClaimStatuses();
//          console.log('self.claimStatuss',self.claimStatuses);
          return self.claimStatuses;
      }

      function getAllClaimStatusDetailes(){
      	self.claimStatusDetailes = ClaimStatusDetailService.getAllClaimStatusDetailes();
    //      console.log('self.claimStatusDetails',self.claimStatusDetailes);
          return self.claimStatusDetailes;
      }

      function getAllProviders() {
          self.prvdrs = ProviderService.getAllProviders();
      //    console.log('self.prvdrs',self.prvdrs);
          return self.prvdrs;
        }
      
      function getAllInsurances() {
          self.insurances = InsuranceService.getAllInsurances();
      //    console.log('self.insurances',self.insurances);
          return self.insurances;
        }
      
      function getAllProviderInsuranceDetailss() {
          self.providerInsuranceDetailss = ProviderInsuranceDetailsService.getAllProviderInsuranceDetailss();
     //     console.log('self.providerInsuranceDetailss',self.providerInsuranceDetailss);
          return self.providerInsuranceDetailss;
        }
      
      function getAllPrioritys() {
          self.prioritys = PriorityService.getAllPrioritys();
      //    console.log('self.prioritys',self.prioritys);
          return self.prioritys;
        }
      
      function getAllPractices() {
          self.practices = PracticeService.getAllPractices();
      //    console.log('self.practices',self.practices);
          return self.practices;
        }

      function editClaim(id) {
        self.successMessage = '';
        self.errorMessage = '';
        ClaimService.getClaim(id).then(
          function(claim) {
            self.claim = claim;

            self.display = true;
          },
          function(errResponse) {
            console.error('Error while removing claim ' + id + ', Error :' + errResponse.data);
          }
        );
      }

      function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.claim = {};
        $scope.myForm.$setPristine(); //reset Form
      }

      function cancelEdit() {
        self.successMessage = '';
        self.errorMessage = '';
        self.claim = {};
        self.display = false;
        $state.go('main.claim', {}, {
          location: true,
          reload: false,
          notify: false
        });
      }

      function claimEdit(id) {
        var params = {
          'claimDisplay': true
        };
        var trans = $state.go('main.claim.edit', params).transition;
        trans.onSuccess({}, function() {
          editClaim(id);
        }, {
          priority: -1
        });
      }

      function addClaim() {
        self.successMessage = '';
        self.errorMessage = '';
        self.display = true;
      }
      
      function setTeamAssignment(teamAssignment){
      self.teamAssignments =teamAssignment;
      alert('self.dt1Instance', JSON.stringify(self.dt1Instance));
      self.dt1Instance.rerender;
      }

    }


  ]);
})();
