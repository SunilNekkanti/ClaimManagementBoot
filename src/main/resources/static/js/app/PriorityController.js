(function() {
  'use strict';
  var app = angular.module('my-app');

  app.controller('PriorityController', ['PriorityService', '$scope', '$compile', '$state', '$stateParams', 'DTOptionsBuilder', 'DTColumnBuilder', function(PriorityService, $scope, $compile, $state, $stateParams, DTOptionsBuilder, DTColumnBuilder) {

      var self = this;
      self.priority = {};
      self.prioritys = [];
      self.display = $stateParams.priorityDisplay || false;;
      self.displayEditButton = false;
      self.submit = submit;
      self.getAllPrioritys = getAllPrioritys;
      self.createPriority = createPriority;
      self.updatePriority = updatePriority;
      self.removePriority = removePriority;
      self.editPriority = editPriority;
      self.addPriority = addPriority;
      self.dtInstance = {};
      self.priorityId = null;
      self.reset = reset;
      self.priorityEdit = priorityEdit;
      self.cancelEdit = cancelEdit;
      self.successMessage = '';
      self.errorMessage = '';
      self.done = false;
      self.onlyIntegers = /^\d+$/;
      self.onlyNumbers = /^\d+([,.]\d+)?$/;
      self.checkBoxChange = checkBoxChange;
      self.dtColumns = [
        DTColumnBuilder.newColumn('code').withTitle('Code').renderWith(
          function(data, type, full,
            meta) {
            return '<a href="javascript:void(0)" class="' + full.id + '" ng-click="ctrl.editPriority(' + full.id + ')">' + data + '</a>';
          }).withClass("text-left").withOption("width", '20%'),
             DTColumnBuilder.newColumn('description').withTitle('Description').withOption("sWidth", '60%').withOption("sClass", ' { max-width: 300px; word-wrap: break-word;  }')
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
        PriorityService
          .loadPrioritys(page, length, search.value, sortCol + ',' + sortDir)
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

      function checkBoxChange(checkStatus, priorityId) {
        self.displayEditButton = checkStatus;
        self.priorityId = priorityId;

      }


      function submit() {
        console.log('Submitting');
        if (self.priority.id === undefined || self.priority.id === null) {
          console.log('Saving New Priority', self.priority);
          createPriority(self.priority);
        } else {
          updatePriority(self.priority, self.priority.id);
          console.log('Priority updated with id ', self.priority.id);
        }
        self.displayEditButton = false;
      }

      function createPriority(priority) {
        console.log('About to create priority');
        PriorityService.createPriority(priority)
          .then(
            function(response) {
              console.log('Priority created successfully');
              self.successMessage = 'Priority created successfully';
              self.errorMessage = '';
              self.done = true;
              self.display = false;
              
              self.priority = {};
              $scope.myForm.$setPristine();
              self.dtInstance.reloadData();
              self.dtInstance.rerender();
            },
            function(errResponse) {
              console.error('Error while creating Priority');
              self.errorMessage = 'Error while creating Priority: ' + errResponse.data.errorMessage;
              self.successMessage = '';
            }
          );
      }


      function updatePriority(priority, id) {
        console.log('About to update priority' + priority);
        PriorityService.updatePriority(priority, id)
          .then(
            function(response) {
              console.log('Priority updated successfully');
              self.successMessage = 'Priority updated successfully';
              self.errorMessage = '';
              self.done = true;
              self.display = false;
              $scope.myForm.$setPristine();
              self.dtInstance.reloadData();
              self.dtInstance.rerender();
            },
            function(errResponse) {
              console.error('Error while updating Priority');
              self.errorMessage = 'Error while updating Priority ' + errResponse.data;
              self.successMessage = '';
            }
          );
      }


      function removePriority(id) {
        console.log('About to remove Priority with id ' + id);
        PriorityService.removePriority(id)
          .then(
            function() {
              console.log('Priority ' + id + ' removed successfully');
              cancelEdit();
            },
            function(errResponse) {
              console.error('Error while removing priority ' + id + ', Error :' + errResponse.data);
            }
          );
      }


      function getAllPrioritys() {
        self.prioritys = PriorityService.getAllPrioritys();

        return self.prioritys;
      }


      function editPriority(id) {
        self.successMessage = '';
        self.errorMessage = '';
        PriorityService.getPriority(id).then(
          function(priority) {
            self.priority = priority;

            self.display = true;
          },
          function(errResponse) {
            console.error('Error while removing priority ' + id + ', Error :' + errResponse.data);
          }
        );
      }

      function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.priority = {};
        $scope.myForm.$setPristine(); //reset Form
      }

      function cancelEdit() {
        self.successMessage = '';
        self.errorMessage = '';
        self.priority = {};
        self.display = false;
        $state.go('main.priority', {}, {
          location: true,
          reload: false,
          notify: false
        });
      }

      function priorityEdit(id) {
    	  console.log('id',id);
        var params = {
          'priorityDisplay': true ,'id':id
        };
        var trans = $state.go('main.priority.edit', params).transition;
        trans.onSuccess({}, function() {
          editPriority(id);
        }, {
          priority: -1
        });
      }

      function addPriority() {
        self.successMessage = '';
        self.errorMessage = '';
        self.display = true;
      }

    }


  ]);
})();
