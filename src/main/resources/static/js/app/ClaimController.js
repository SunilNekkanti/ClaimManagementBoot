(function() {
  'use strict';
  var app = angular.module('my-app');

  app.controller('ClaimController', ['ClaimService', 'ProviderService', 'InsuranceService', 'ClaimStatusService', 'ClaimStatusDetailService', 'ProviderInsuranceDetailsService', 'PriorityService', 'PracticeService', '$scope', '$compile', '$state', '$stateParams', 'DTOptionsBuilder', 'DTColumnBuilder', function(ClaimService, ProviderService, InsuranceService, ClaimStatusService, ClaimStatusDetailService, ProviderInsuranceDetailsService, PriorityService, PracticeService, $scope, $compile, $state, $stateParams, DTOptionsBuilder, DTColumnBuilder) {

    var self = this;
    self.claim = {};
    self.claims = [];
    self.prvdrs = [];
    self.insurances = [];
    self.claimStatuss = [];
    self.claimStatusDetails = [];
    self.providerInsuranceDetailss = [];
    self.practices = [];
    self.prioritys = [];
    self.displayTables = [true, false, false, false, false, false, false];
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
    self.dtInstance = {};
    self.dt1Instance = {};
    self.dt2Instance = {};
    self.dt3Instance = {};
    self.dt4Instance = {};
    self.dt5Instance = {};
    self.dt6Instance = {};
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
    self.teamAssignments = 0;
    self.allocationDate = '';
    self.practices = '';
    self.remarks = '';
    self.srvcDtFrom = '';
    self.srvcDtTo = '';
    self.patientName = '';
    self.birthDate = '';
    self.selectedInsurances = '';
    self.insuranceTypes = '';
    self.chargesMin = 0;
    self.chargesMax = 9999999;
    self.claimStatus = '';
    self.priorities = '';
    self.userName = '';

console.log('insurnaces***********',self.insurances);
    if (self.practices != null && self.practices.length > 0) {
      self.practice = self.practice || self.practices[0];
    } else {
      self.practice = {};
    }



    self.dtColumns = [
      DTColumnBuilder.newColumn('lookup').withTitle('LOOKUP').renderWith(
        function(data, type, full,
          meta) {
          return '<a href="javascript:void(0)" class="' + full.id + '" ng-click="ctrl.claimEdit(' + full.id + ')">' + data + '</a>';
        }).withClass("text-left").withOption("width", '10%'),
      DTColumnBuilder.newColumn('serviceDate').withTitle('SERVICE_DATE').withOption("width", '40%'),
      DTColumnBuilder.newColumn('patient').withTitle('PATIENT_NAME').withOption("width", '400%'),
      DTColumnBuilder.newColumn('dob').withTitle('BIRTH_DATE').withOption("width", '20%'),
      DTColumnBuilder.newColumn('insurance').withTitle('INSURANCE').withOption("width", '20%'),
      DTColumnBuilder.newColumn('insuranceType').withTitle('INSURANCE_TYPE').withOption("width", '20%'),
      DTColumnBuilder.newColumn('charges').withTitle('CHARGES').withOption("width", '20%'),
      DTColumnBuilder.newColumn('statuses').withTitle('STATUS').withOption("width", '20%'),
      DTColumnBuilder.newColumn('priority').withTitle('PRIORITY').withOption("width", '20%')

    ];

    

    self.dtOptions = DTOptionsBuilder.newOptions().withBootstrap()
      .withDOM('ftip')
      .withOption('initComplete', function() {
      console.log("before initializing table");
      angular.element('.dataTables_filter input').attr('placeholder', 'Search');
          })
      .withDisplayLength(20)
      .withOption('bServerSide', true)
      .withOption('bSort', false)
      .withOption('searchDelay', 2500)
      .withOption('bProcessing', true)
      .withOption('bResponsive', true)
      .withOption("bPaginate", true)
      .withPaginationType('full_numbers')
      .withOption('stateSave', true)
      .withOption('createdRow', createdRow)
      .withOption('bDeferRender', false)
      .withOption('scrollY', '450')
      .withOption('scrollX', '716')
      .withColReorder()
      .withFixedHeader({
        bottom: true
      })
      .withLightColumnFilter({
        '0': {},
        '1': {
          html: 'range',
          type: 'dateRange',
          time: 200,
        },
        '2': {
          html: 'input',
          type: 'text',
          time: 200,
        },
        '3': {
          html: 'input',
          type: 'text'
        },
        '4': {
          html: 'input',
          type: 'select',
          value:self.insurances
        },
        '5': {
          html: 'input',
          type: 'text'
        }
      })
      .withOption('language', {
          'processing': '<div class="table-logo-wrapper"><div class="vloader"></div></div>'
         // 'url': $cookies.get('JSESSION')
        })
      .withFnServerData(serverData)
      .withOption('bDestroy', true);


    
  //  setTeamAssignment(0);

    function createdRow(row, data, dataIndex) {
      // Recompiling so we can bind Angular directive to the DT
      $compile(angular.element(row).contents())($scope);
    }

    function checkBoxChange(checkStatus, claimId) {
      self.displayEditButton = checkStatus;
      self.claimId = claimId;

    }

    function serverData(sSource, aoData, fnCallback) {
      // All the parameters you need is in the aoData
      // variable
      var pracId = (self.practice === undefined || self.practice === null) ? 0 : self.practice.id;
      var order = aoData[2].value;
      var page = aoData[3].value / aoData[4].value;
      var length = aoData[4].value;
      var search = aoData[5].value;
      
      self.serviceDate = aoData[1].value[1]['search'].value || '';
      self.patientName = aoData[1].value[2]['search'].value || '';
      self.birthDate = aoData[1].value[3]['search'].value || '';
      self.selectedInsurances = aoData[1].value[4]['search'].value || '';
      self.insuranceTypes = aoData[1].value[5]['search'].value || '';
      
       console.log ('self.insurances',self.insurances );
      

      var paramMap = {};
      for (var i = 0; i < aoData.length; i++) {
        paramMap[aoData[i].name] = aoData[i].value;
      }

      var sortCol = '';
      var sortDir = '';
      // extract sort information
      //  if (paramMap['columns'] !== undefined && paramMap['columns'] !== null && paramMap['order'] !== undefined && paramMap['order'] !== null) {
      //     sortCol = paramMap['columns'][paramMap['order'][0]['column']].data;
      //     sortDir = paramMap['order'][0]['dir'];
      //  }
      // Then just call your service to get the
      // records from server side
      ClaimService
        .loadClaims(page, length, search.value, sortCol, sortDir,
          self.teamAssignments, self.serviceDate,
          self.practices, self.remarks, self.srvcDtFrom, self.srvcDtTo, self.patientName, self.birthDate, self.selectedInsurances,
          self.insuranceTypes, self.chargesMin, self.chargesMax, self.claimStatus, self.priorities, self.userName
        )
        .then(
          function(result) {
            var records = {
              'recordsTotal': result.data.totalElements || 0,
              'recordsFiltered': result.data.totalElements || 0,
              'data': result.data.content || {}
            };
            fnCallback(records);

          });
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

    function getAllClaimStatuses() {
      self.claimStatuses = ClaimStatusService.getAllClaimStatuses();
      return self.claimStatuses;
    }

    function getAllClaimStatusDetailes() {
      self.claimStatusDetailes = ClaimStatusDetailService.getAllClaimStatusDetailes();
      return self.claimStatusDetailes;
    }

    function getAllProviders() {
      self.prvdrs = ProviderService.getAllProviders();
      return self.prvdrs;
    }

    function getAllInsurances() {
      self.insurances = InsuranceService.getAllInsurances();
      return self.insurances;
    }

    function getAllProviderInsuranceDetailss() {
      self.providerInsuranceDetailss = ProviderInsuranceDetailsService.getAllProviderInsuranceDetailss();
      return self.providerInsuranceDetailss;
    }

    function getAllPrioritys() {
      self.prioritys = PriorityService.getAllPrioritys();
      return self.prioritys;
    }

    function getAllPractices() {
      self.practices = PracticeService.getAllPractices();
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

    function setTeamAssignment(teamAssignment) {
      self.teamAssignments = teamAssignment;
      self.displayTables.forEach(function(displayTable, index) {
        if (teamAssignment == index) {
          self.displayTables[index] = true;
        } else {
          self.displayTables[index] = false;
        }
      });
      switch (self.teamAssignments) {
        case 1:
          if (!angular.equals(self.dt1Instance, {})) {
            self.dt1Instance.rerender();
          }
          break;
        case 2:
          if (!angular.equals(self.dt2Instance, {})) {
            self.dt2Instance.rerender();
          }
          break;
        case 3:
          if (!angular.equals(self.dt3Instance, {})) {
            self.dt3Instance.rerender();
          }
          break;
        case 4:
          if (!angular.equals(self.dt4Instance, {})) {
            self.dt4Instance.rerender();
          }
          break;
        case 5:
          if (!angular.equals(self.dt5Instance, {})) {
            self.dt5Instance.rerender();
          }
          break;
        case 6:
          if (!angular.equals(self.dt6Instance, {})) {
            self.dt6Instance.rerender();
          }
          break;
        default:
          if (!angular.equals(self.dtInstance, {})) {
            self.dtInstance.rerender();
          }
          break
      }

    }


  }]);
})();