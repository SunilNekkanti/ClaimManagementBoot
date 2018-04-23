(function() {
  'use strict';
  var app = angular.module('my-app');

  app.controller('TargetController', ['TargetService', '$scope', '$compile', '$state', '$stateParams', 'DTOptionsBuilder', 'DTColumnBuilder', function(TargetService, $scope, $compile, $state, $stateParams, DTOptionsBuilder, DTColumnBuilder) {

      var self = this;
      self.target = {};
      self.targets = [];
      self.display = $stateParams.targetDisplay || false;;
      self.displayEditButton = false;
      self.submit = submit;
      self.getAllTargets = getAllTargets;
      self.createTarget = createTarget;
      self.updateTarget = updateTarget;
      self.removeTarget = removeTarget;
      self.editTarget = editTarget;
      self.addTarget = addTarget;
      self.dtInstance = {};
      self.targetId = null;
      self.reset = reset;
      self.targetEdit = targetEdit;
      self.cancelEdit = cancelEdit;
      self.successMessage = '';
      self.errorMessage = '';
      self.done = false;
      self.onlyIntegers = /^\d+$/;
      self.onlyNumbers = /^\d+([,.]\d+)?$/;
      self.checkBoxChange = checkBoxChange;
      self.dtColumns = [
        DTColumnBuilder.newColumn('description').withTitle('NAME').renderWith(
          function(data, type, full,
            meta) {
            return '<a href="javascript:void(0)" class="' + full.id + '" ng-click="ctrl.targetEdit(' + full.id + ')">' + data + '</a>';
          }).withClass("text-left").withOption("width", '20%'),
        DTColumnBuilder.newColumn('targetCount').withTitle('COUNT').withOption("width", '20%')
      ];


      self.dtOptions = DTOptionsBuilder.newOptions()
       .withDisplayLength(20)
        .withOption('bServerSide', true)
        .withOption('responsive', true)
        .withOption("bLengthChange", false)
        .withOption("bPaginate", true)
        .withOption('bProcessing', true)
        .withOption('stateSave', true)
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
        TargetService
          .loadTargets(page, length, search.value, sortCol + ',' + sortDir)
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

      function checkBoxChange(checkStatus, targetId) {
        self.displayEditButton = checkStatus;
        self.targetId = targetId;

      }


      function submit() {
        console.log('Submitting');
        if (self.target.id === undefined || self.target.id === null) {
          console.log('Saving New Target', self.target);
          createTarget(self.target);
        } else {
          updateTarget(self.target, self.target.id);
          console.log('Target updated with id ', self.target.id);
        }
        self.displayEditButton = false;
      }

      function createTarget(target) {
        console.log('About to create target');
        TargetService.createTarget(target)
          .then(
            function(response) {
              console.log('Target created successfully');
              self.successMessage = 'Target created successfully';
              self.errorMessage = '';
              self.done = true;
              self.display = false;
             
              self.target = {};
              $scope.myForm.$setPristine();
              self.dtInstance.reloadData();
              self.dtInstance.rerender();

            },
            function(errResponse) {
              console.error('Error while creating Target');
              self.errorMessage = 'Error while creating Target: ' + errResponse.data.errorMessage;
              self.successMessage = '';
            }
          );
      }


      function updateTarget(target, id) {
        console.log('About to update target' + target);
        TargetService.updateTarget(target, id)
          .then(
            function(response) {
              console.log('Target updated successfully');
              self.successMessage = 'Target updated successfully';
              self.errorMessage = '';
              self.done = true;
              self.display = false;
              $scope.myForm.$setPristine();
              self.dtInstance.reloadData();
              self.dtInstance.rerender();
            },
            function(errResponse) {
              console.error('Error while updating Target');
              self.errorMessage = 'Error while updating Target ' + errResponse.data;
              self.successMessage = '';
            }
          );
      }


      function removeTarget(id) {
        console.log('About to remove Target with id ' + id);
        TargetService.removeTarget(id)
          .then(
            function() {
              console.log('Target ' + id + ' removed successfully');
              cancelEdit();
            },
            function(errResponse) {
              console.error('Error while removing target ' + id + ', Error :' + errResponse.data);
            }
          );
      }


      function getAllTargets() {
        self.targets = TargetService.getAllTargets();

        return self.targets;
      }


      function editTarget(id) {
        self.successMessage = '';
        self.errorMessage = '';
        TargetService.getTarget(id).then(
          function(target) {
            self.target = target;

            self.display = true;
          },
          function(errResponse) {
            console.error('Error while removing target ' + id + ', Error :' + errResponse.data);
          }
        );
      }

      function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.target = {};
        $scope.myForm.$setPristine(); //reset Form
      }

      function cancelEdit() {
        self.successMessage = '';
        self.errorMessage = '';
        self.target = {};
        self.display = false;
        $state.go('main.target', {}, {
          location: true,
          reload: false,
          notify: false
        });
      }

      function targetEdit(id) {
        var params = {
          'targetDisplay': true
        };
        var trans = $state.go('main.target.edit', params).transition;
        trans.onSuccess({}, function() {
          editTarget(id);
        }, {
          priority: -1
        });
      }

      function addTarget() {
        self.successMessage = '';
        self.errorMessage = '';
        self.display = true;
      }

    }


  ]);
})();
