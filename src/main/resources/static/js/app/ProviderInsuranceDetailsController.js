(function() {
  'use strict';
  var app = angular.module('my-app');

  app.controller('ProviderInsuranceDetailsController', ['ProviderInsuranceDetailsService','ProviderService','InsuranceService','PracticeService','RoleService', '$scope', '$compile', '$state', '$stateParams', 'DTOptionsBuilder', 'DTColumnBuilder', function(ProviderInsuranceDetailsService,ProviderService,InsuranceService,PracticeService,RoleService, $scope, $compile, $state, $stateParams, DTOptionsBuilder, DTColumnBuilder) {

      var self = this;
      self.providerInsuranceDetails = {};
      self.providerInsuranceDetailss = [];
      self.prvdrs = [];
      self.insurances = [];
      self.practices = [];
      self.roles = [];
      self.display = $stateParams.providerInsuranceDetailsDisplay || false;;
      self.displayEditButton = false;
      self.submit = submit;
      self.getAllProviderInsuranceDetailss = getAllProviderInsuranceDetailss;
      self.getAllProviders = getAllProviders;
      self.getAllInsurances = getAllInsurances;
      self.getAllPractices = getAllPractices;
      self.getAllRoles = getAllRoles;
      
      self.prvdrs = getAllProviders();
      self.insurances = getAllInsurances();
      self.practices = getAllPractices();
      self.roles = getAllRoles();
      
      self.createProviderInsuranceDetails = createProviderInsuranceDetails;
      self.updateProviderInsuranceDetails = updateProviderInsuranceDetails;
      self.removeProviderInsuranceDetails = removeProviderInsuranceDetails;
      self.editProviderInsuranceDetails = editProviderInsuranceDetails;
      self.addProviderInsuranceDetails = addProviderInsuranceDetails;
      self.dtInstance = {};
      self.providerInsuranceDetailsId = null;
      self.reset = reset;
      self.providerInsuranceDetailsEdit = providerInsuranceDetailsEdit;
      self.cancelEdit = cancelEdit;
      self.successMessage = '';
      self.errorMessage = '';
      self.done = false;
      self.onlyIntegers = /^\d+$/;
      self.onlyNumbers = /^\d+([,.]\d+)?$/;
      self.checkBoxChange = checkBoxChange;
      self.dtColumns = [
        DTColumnBuilder.newColumn('id').withTitle('Id').renderWith(
          function(data, type, full,
            meta) {
            return '<a href="javascript:void(0)" class="' + full.id + '" ng-click="ctrl.providerInsuranceDetailsEdit(' + full.id + ')">' + data + '</a>';
          }).withClass("text-left").withOption("width", '20%'),
        DTColumnBuilder.newColumn('prvdr.name').withTitle('Provider').withOption("width", '20%'),
        DTColumnBuilder.newColumn('ins.name').withTitle('Insurance').withOption("width", '20%'),
        DTColumnBuilder.newColumn('prac.name').withTitle('Practice').withOption("width", '20%'),
        DTColumnBuilder.newColumn('role.role').withTitle('Role').withOption("width", '20%'),
        DTColumnBuilder.newColumn('url').withTitle('URL').withOption("width", '20%'),
        DTColumnBuilder.newColumn('urlUserName').withTitle('URL Username').withOption("width", '20%'),
        DTColumnBuilder.newColumn('urlPassword').withTitle('URL Password').withOption("width", '20%'),
        DTColumnBuilder.newColumn('urlPasswordActive').withTitle('URL PasswordActive').withOption("width", '20%'),
        DTColumnBuilder.newColumn('isClearingHouse').withTitle('IsClearingHouse').withOption("width", '20%')
      ];


      self.dtOptions = DTOptionsBuilder.newOptions()
       .withDisplayLength(20)
        .withOption('bServerSide', true)
        .withOption('responsive', true)
        .withOption("bLengthChange", false)
        .withOption("bPaginate", true)
        .withOption('bProcessing', true)
        .withOption('bSaveState', true)
        .withOption('searchDelay', 2000)
        .withOption('createdRow', createdRow)
        .withPaginationType('full_numbers')
        .withOption('ordering', true)
        .withOption('order', [
          [0, 'ASC']
        ])
        .withOption('aLengthMenu', [
          [15, 20, -1],
          [15, 20, "All"]
        ])
        .withOption('bDeferRender', true)
        .withFnServerData(serverData);

      function serverData(sSource, aoData, fnCallback) {


        // All the parameters you need is in the aoData
        // variable
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
        ProviderInsuranceDetailsService
          .loadProviderInsuranceDetailss(page, length, search.value, sortCol + ',' + sortDir)
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

      function reloadData() {
        var resetPaging = false;
        self.dtInstance.reloadData(callback,
          resetPaging);
      }

      function createdRow(row, data, dataIndex) {
        // Recompiling so we can bind Angular directive to the DT
        $compile(angular.element(row).contents())($scope);
      }

      function checkBoxChange(checkStatus, providerInsuranceDetailsId) {
        self.displayEditButton = checkStatus;
        self.providerInsuranceDetailsId = providerInsuranceDetailsId;

      }


      function submit() {
        console.log('Submitting');
        if (self.providerInsuranceDetails.id === undefined || self.providerInsuranceDetails.id === null) {
          console.log('Saving New ProviderInsuranceDetails', self.providerInsuranceDetails);
          createProviderInsuranceDetails(self.providerInsuranceDetails);
        } else {
          updateProviderInsuranceDetails(self.providerInsuranceDetails, self.providerInsuranceDetails.id);
          console.log('ProviderInsuranceDetails updated with id ', self.providerInsuranceDetails.id);
        }
        self.displayEditButton = false;
      }

      function createProviderInsuranceDetails(providerInsuranceDetails) {
        console.log('About to create providerInsuranceDetails');
        ProviderInsuranceDetailsService.createProviderInsuranceDetails(providerInsuranceDetails)
          .then(
            function(response) {
              console.log('ProviderInsuranceDetails created successfully');
              self.successMessage = 'ProviderInsuranceDetails created successfully';
              self.errorMessage = '';
              self.done = true;
              self.display = false;
             
              self.providerInsuranceDetails = {};
              $scope.myForm.$setPristine();
              self.dtInstance.reloadData();
              self.dtInstance.rerender();

            },
            function(errResponse) {
              console.error('Error while creating ProviderInsuranceDetails');
              self.errorMessage = 'Error while creating ProviderInsuranceDetails: ' + errResponse.data.errorMessage;
              self.successMessage = '';
            }
          );
      }


      function updateProviderInsuranceDetails(providerInsuranceDetails, id) {
        console.log('About to update providerInsuranceDetails' + providerInsuranceDetails);
        ProviderInsuranceDetailsService.updateProviderInsuranceDetails(providerInsuranceDetails, id)
          .then(
            function(response) {
              console.log('ProviderInsuranceDetails updated successfully');
              self.successMessage = 'ProviderInsuranceDetails updated successfully';
              self.errorMessage = '';
              self.done = true;
              self.display = false;
              $scope.myForm.$setPristine();
              self.dtInstance.reloadData();
              self.dtInstance.rerender();
            },
            function(errResponse) {
              console.error('Error while updating ProviderInsuranceDetails');
              self.errorMessage = 'Error while updating ProviderInsuranceDetails ' + errResponse.data;
              self.successMessage = '';
            }
          );
      }


      function removeProviderInsuranceDetails(id) {
        console.log('About to remove ProviderInsuranceDetails with id ' + id);
        ProviderInsuranceDetailsService.removeProviderInsuranceDetails(id)
          .then(
            function() {
              console.log('ProviderInsuranceDetails ' + id + ' removed successfully');
              cancelEdit();
            },
            function(errResponse) {
              console.error('Error while removing providerInsuranceDetails ' + id + ', Error :' + errResponse.data);
            }
          );
      }


      function getAllProviderInsuranceDetailss() {
        self.providerInsuranceDetailss = ProviderInsuranceDetailsService.getAllProviderInsuranceDetailss();

        return self.providerInsuranceDetailss;
      }
         
      function getAllProviders() {
          self.prvdrs = ProviderService.getAllProviders();
          console.log('self.prvdrs',self.prvdrs);
          return self.prvdrs;
        }
      function getAllInsurances() {
          self.insurances = InsuranceService.getAllInsurances();
          console.log('self.insurances',self.insurances);
          return self.insurances;
        }
      function getAllPractices() {
          self.practices = PracticeService.getAllPractices();
          console.log('self.practices',self.practices);
          return self.practices;
        }
      function getAllRoles() {
          self.roles = RoleService.getAllRoles();
          console.log('self.roles',self.roles);
          return self.roles;
        }

      function editProviderInsuranceDetails(id) {
        self.successMessage = '';
        self.errorMessage = '';
        ProviderInsuranceDetailsService.getProviderInsuranceDetails(id).then(
          function(providerInsuranceDetails) {
            self.providerInsuranceDetails = providerInsuranceDetails;

            self.display = true;
          },
          function(errResponse) {
            console.error('Error while removing providerInsuranceDetails ' + id + ', Error :' + errResponse.data);
          }
        );
      }

      function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.providerInsuranceDetails = {};
        $scope.myForm.$setPristine(); //reset Form
      }

      function cancelEdit() {
        self.successMessage = '';
        self.errorMessage = '';
        self.providerInsuranceDetails = {};
        self.display = false;
        $state.go('main.providerInsuranceDetails', {}, {
          location: true,
          reload: false,
          notify: false
        });
      }

      function providerInsuranceDetailsEdit(id) {
        var params = {
          'providerInsuranceDetailsDisplay': true
        };
        var trans = $state.go('main.providerInsuranceDetails.edit', params).transition;
        trans.onSuccess({}, function() {
          editProviderInsuranceDetails(id);
        }, {
          priority: -1
        });
      }

      function addProviderInsuranceDetails() {
        self.successMessage = '';
        self.errorMessage = '';
        self.display = true;
      }

    }


  ]);
})();
