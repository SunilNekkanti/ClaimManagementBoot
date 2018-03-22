(function() {
  'use strict';
  var app = angular.module('my-app');

  app.controller('WeightageController', ['WeightageService','WeightageTypeService', '$scope', '$compile', '$state', '$stateParams', 'DTOptionsBuilder', 'DTColumnBuilder', function(WeightageService,WeightageTypeService, $scope, $compile, $state, $stateParams, DTOptionsBuilder, DTColumnBuilder) {

      var self = this;
      self.weightage = {};
      self.weightages = [];
      self.weightageTypes = [];
      self.display = $stateParams.weightageDisplay || false;;
      self.displayEditButton = false;
      self.submit = submit;
      self.getAllWeightages = getAllWeightages;
      self.getAllWeightageTypes = getAllWeightageTypes;
      self.weightageTypes = getAllWeightageTypes();
      self.createWeightage = createWeightage;
      self.updateWeightage = updateWeightage;
      self.removeWeightage = removeWeightage;
      self.editWeightage = editWeightage;
      self.addWeightage = addWeightage;
      self.dtInstance = {};
      self.weightageId = null;
      self.reset = reset;
      self.weightageEdit = weightageEdit;
      self.cancelEdit = cancelEdit;
      self.successMessage = '';
      self.errorMessage = '';
      self.done = false;
      self.onlyIntegers = /^\d+$/;
      self.onlyNumbers = /^\d+([,.]\d+)?$/;
      self.checkBoxChange = checkBoxChange;
      self.dtColumns = [
        DTColumnBuilder.newColumn('wtageType.description').withTitle('Weightage').renderWith(
          function(data, type, full,
            meta) {
            return '<a href="javascript:void(0)" class="' + full.id + '" ng-click="ctrl.weightageEdit(' + full.id + ')">' + data + '</a>';
          }).withClass("text-left").withOption("width", '30%'),
             DTColumnBuilder.newColumn('conditionExp').withTitle('Condition').withOption("sWidth", '30%'),
             DTColumnBuilder.newColumn('percentage').withTitle('Percentage').withOption("sWidth", '30%')
      ];


      self.dtOptions = DTOptionsBuilder.newOptions()
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
        WeightageService
          .loadWeightages(page, length, search.value, sortCol + ',' + sortDir)
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

      function checkBoxChange(checkStatus, weightageId) {
        self.displayEditButton = checkStatus;
        self.weightageId = weightageId;

      }


      function submit() {
        console.log('Submitting');
        if (self.weightage.id === undefined || self.weightage.id === null) {
          console.log('Saving New Weightage', self.weightage);
          createWeightage(self.weightage);
        } else {
          updateWeightage(self.weightage, self.weightage.id);
          console.log('Weightage updated with id ', self.weightage.id);
        }
        self.displayEditButton = false;
      }

      function createWeightage(weightage) {
        console.log('About to create weightage');
        WeightageService.createWeightage(weightage)
          .then(
            function(response) {
              console.log('Weightage created successfully');
              self.successMessage = 'Weightage created successfully';
              self.errorMessage = '';
              self.done = true;
              self.display = false;
              
              self.weightage = {};
              $scope.myForm.$setPristine();
              self.dtInstance.reloadData();
              self.dtInstance.rerender();

            },
            function(errResponse) {
              console.error('Error while creating Weightage');
              self.errorMessage = 'Error while creating Weightage: ' + errResponse.data.errorMessage;
              self.successMessage = '';
            }
          );
      }


      function updateWeightage(weightage, id) {
        console.log('About to update weightage' + weightage);
        WeightageService.updateWeightage(weightage, id)
          .then(
            function(response) {
              console.log('Weightage updated successfully');
              self.successMessage = 'Weightage updated successfully';
              self.errorMessage = '';
              self.done = true;
              self.display = false;
              $scope.myForm.$setPristine();
              self.dtInstance.reloadData();
              self.dtInstance.rerender();
            },
            function(errResponse) {
              console.error('Error while updating Weightage');
              self.errorMessage = 'Error while updating Weightage ' + errResponse.data;
              self.successMessage = '';
            }
          );
      }


      function removeWeightage(id) {
        console.log('About to remove Weightage with id ' + id);
        WeightageService.removeWeightage(id)
          .then(
            function() {
              console.log('Weightage ' + id + ' removed successfully');
              cancelEdit();
            },
            function(errResponse) {
              console.error('Error while removing weightage ' + id + ', Error :' + errResponse.data);
            }
          );
      }


      function getAllWeightages() {
        self.weightages = WeightageService.getAllWeightages();

        return self.weightages;
      }
      
      function getAllWeightageTypes() {
          self.weightageTypes = WeightageTypeService.getAllWeightageTypes();
          console.log('self.weightageTypes',self.weightageTypes);
          return self.weightageTypes;
        }

      function editWeightage(id) {
        self.successMessage = '';
        self.errorMessage = '';
        WeightageService.getWeightage(id).then(
          function(weightage) {
            self.weightage = weightage;

            self.display = true;
          },
          function(errResponse) {
            console.error('Error while removing weightage ' + id + ', Error :' + errResponse.data);
          }
        );
      }

      function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.weightage = {};
        $scope.myForm.$setPristine(); //reset Form
      }

      function cancelEdit() {
        self.successMessage = '';
        self.errorMessage = '';
        self.weightage = {};
        self.display = false;
        $state.go('main.weightage', {}, {
          location: true,
          reload: false,
          notify: false
        });
      }

      function weightageEdit(id) {
        var params = {
          'weightageDisplay': true
        };
        var trans = $state.go('main.weightage.edit', params).transition;
        trans.onSuccess({}, function() {
          editWeightage(id);
        }, {
          weightage: -1
        });
      }

      function addWeightage() {
        self.successMessage = '';
        self.errorMessage = '';
        self.display = true;
      }

    }


  ]);
})();
