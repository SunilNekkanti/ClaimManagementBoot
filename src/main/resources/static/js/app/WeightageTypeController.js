(function() {
  'use strict';
  var app = angular.module('my-app');

  app.controller('WeightageTypeController', ['WeightageTypeService', '$scope', '$compile', '$state', '$stateParams', 'DTOptionsBuilder', 'DTColumnBuilder', function(WeightageTypeService, $scope, $compile, $state, $stateParams, DTOptionsBuilder, DTColumnBuilder) {

      var self = this;
      self.weightageType = {};
      self.weightageTypes = [];
      self.display = $stateParams.weightageTypeDisplay || false;;
      self.displayEditButton = false;
      self.submit = submit;
      self.getAllWeightageTypes = getAllWeightageTypes;
      self.createWeightageType = createWeightageType;
      self.updateWeightageType = updateWeightageType;
      self.removeWeightageType = removeWeightageType;
      self.editWeightageType = editWeightageType;
      self.addWeightageType = addWeightageType;
      self.dtInstance = {};
      self.weightageTypeId = null;
      self.reset = reset;
      self.weightageTypeEdit = weightageTypeEdit;
      self.cancelEdit = cancelEdit;
      self.successMessage = '';
      self.errorMessage = '';
      self.done = false;
      self.onlyIntegers = /^\d+$/;
      self.onlyNumbers = /^\d+([,.]\d+)?$/;
      self.checkBoxChange = checkBoxChange;
      self.dtColumns = [
        DTColumnBuilder.newColumn('description').withTitle('Name').renderWith(
          function(data, type, full,
            meta) {
            return '<a href="javascript:void(0)" class="' + full.id + '" ng-click="ctrl.weightageTypeEdit(' + full.id + ')">' + data + '</a>';
          }).withClass("text-left").withOption("width", '20%'),
        DTColumnBuilder.newColumn('weightagePercent').withTitle('Weightage Percent').withOption("width", '20%')
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
        WeightageTypeService
          .loadWeightageTypes(page, length, search.value, sortCol + ',' + sortDir)
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

      function checkBoxChange(checkStatus, weightageTypeId) {
        self.displayEditButton = checkStatus;
        self.weightageTypeId = weightageTypeId;

      }


      function submit() {
        console.log('Submitting');
        if (self.weightageType.id === undefined || self.weightageType.id === null) {
          console.log('Saving New WeightageType', self.weightageType);
          createWeightageType(self.weightageType);
        } else {
          updateWeightageType(self.weightageType, self.weightageType.id);
          console.log('WeightageType updated with id ', self.weightageType.id);
        }
        self.displayEditButton = false;
      }

      function createWeightageType(weightageType) {
        console.log('About to create weightageType');
        WeightageTypeService.createWeightageType(weightageType)
          .then(
            function(response) {
              console.log('WeightageType created successfully');
              self.successMessage = 'WeightageType created successfully';
              self.errorMessage = '';
              self.done = true;
              self.display = false;
             
              self.weightageType = {};
              $scope.myForm.$setPristine();
              self.dtInstance.reloadData();
              self.dtInstance.rerender();

            },
            function(errResponse) {
              console.error('Error while creating WeightageType');
              self.errorMessage = 'Error while creating WeightageType: ' + errResponse.data.errorMessage;
              self.successMessage = '';
            }
          );
      }


      function updateWeightageType(weightageType, id) {
        console.log('About to update weightageType' + weightageType);
        WeightageTypeService.updateWeightageType(weightageType, id)
          .then(
            function(response) {
              console.log('WeightageType updated successfully');
              self.successMessage = 'WeightageType updated successfully';
              self.errorMessage = '';
              self.done = true;
              self.display = false;
              $scope.myForm.$setPristine();
              self.dtInstance.reloadData();
              self.dtInstance.rerender();
            },
            function(errResponse) {
              console.error('Error while updating WeightageType');
              self.errorMessage = 'Error while updating WeightageType ' + errResponse.data;
              self.successMessage = '';
            }
          );
      }


      function removeWeightageType(id) {
        console.log('About to remove WeightageType with id ' + id);
        WeightageTypeService.removeWeightageType(id)
          .then(
            function() {
              console.log('WeightageType ' + id + ' removed successfully');
              cancelEdit();
            },
            function(errResponse) {
              console.error('Error while removing weightageType ' + id + ', Error :' + errResponse.data);
            }
          );
      }


      function getAllWeightageTypes() {
        self.weightageTypes = WeightageTypeService.getAllWeightageTypes();

        return self.weightageTypes;
      }


      function editWeightageType(id) {
        self.successMessage = '';
        self.errorMessage = '';
        WeightageTypeService.getWeightageType(id).then(
          function(weightageType) {
            self.weightageType = weightageType;

            self.display = true;
          },
          function(errResponse) {
            console.error('Error while removing weightageType ' + id + ', Error :' + errResponse.data);
          }
        );
      }

      function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.weightageType = {};
        $scope.myForm.$setPristine(); //reset Form
      }

      function cancelEdit() {
        self.successMessage = '';
        self.errorMessage = '';
        self.weightageType = {};
        self.display = false;
        $state.go('main.weightageType', {}, {
          location: true,
          reload: false,
          notify: false
        });
      }

      function weightageTypeEdit(id) {
        var params = {
          'weightageTypeDisplay': true
        };
        var trans = $state.go('main.weightageType.edit', params).transition;
        trans.onSuccess({}, function() {
          editWeightageType(id);
        }, {
          priority: -1
        });
      }

      function addWeightageType() {
        self.successMessage = '';
        self.errorMessage = '';
        self.display = true;
      }

    }


  ]);
})();
