(function() {
  'use strict';
  var app = angular.module('my-app');

  app.controller('PracticeController', ['PracticeService', '$scope', '$compile', '$state', '$stateParams', 'DTOptionsBuilder', 'DTColumnBuilder', function(PracticeService, $scope, $compile, $state, $stateParams, DTOptionsBuilder, DTColumnBuilder) {

      var self = this;
      self.practice = {};
      self.practices = [];
      self.display = $stateParams.practiceDisplay || false;;
      self.displayEditButton = false;
      self.submit = submit;
      self.getAllPractices = getAllPractices;
      self.createPractice = createPractice;
      self.updatePractice = updatePractice;
      self.removePractice = removePractice;
      self.editPractice = editPractice;
      self.addPractice = addPractice;
      self.dtInstance = {};
      self.practiceId = null;
      self.reset = reset;
      self.practiceEdit = practiceEdit;
      self.cancelEdit = cancelEdit;
      self.successMessage = '';
      self.errorMessage = '';
      self.done = false;
      self.onlyIntegers = /^\d+$/;
      self.onlyNumbers = /^\d+([,.]\d+)?$/;
      self.checkBoxChange = checkBoxChange;
      self.dtColumns = [
        DTColumnBuilder.newColumn('shortName').withTitle('ShortName').renderWith(
          function(data, type, full,
            meta) {
            return '<a href="javascript:void(0)" class="' + full.id + '" ng-click="ctrl.practiceEdit(' + full.id + ')">' + data + '</a>';
          }).withClass("text-left").withOption("width", '30%'),
             DTColumnBuilder.newColumn('name').withTitle('Name').withOption("sWidth", '30%'),
             DTColumnBuilder.newColumn('npi').withTitle('NPI').withOption("sWidth", '30%'),
             DTColumnBuilder.newColumn('taxId').withTitle('TaxID').withOption("sWidth", '30%'),
             DTColumnBuilder.newColumn('clearingHouseURL').withTitle('Clearing House URL'),
             DTColumnBuilder.newColumn('userName').withTitle('UserName').withOption("sWidth", '30%'),
             DTColumnBuilder.newColumn('password').withTitle('Password').withOption("sWidth", '30%')
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
        PracticeService
          .loadPractices(page, length, search.value, sortCol + ',' + sortDir)
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

      function checkBoxChange(checkStatus, practiceId) {
        self.displayEditButton = checkStatus;
        self.practiceId = practiceId;

      }


      function submit() {
        console.log('Submitting');
        if (self.practice.id === undefined || self.practice.id === null) {
          console.log('Saving New Practice', self.practice);
          createPractice(self.practice);
        } else {
          updatePractice(self.practice, self.practice.id);
          console.log('Practice updated with id ', self.practice.id);
        }
        self.displayEditButton = false;
      }

      function createPractice(practice) {
        console.log('About to create practice');
        PracticeService.createPractice(practice)
          .then(
            function(response) {
              console.log('Practice created successfully');
              self.successMessage = 'Practice created successfully';
              self.errorMessage = '';
              self.done = true;
              self.display = false;
              
              self.practice = {};
              $scope.myForm.$setPristine();
              self.dtInstance.reloadData();
              self.dtInstance.rerender();

            },
            function(errResponse) {
              console.error('Error while creating Practice');
              self.errorMessage = 'Error while creating Practice: ' + errResponse.data.errorMessage;
              self.successMessage = '';
            }
          );
      }


      function updatePractice(practice, id) {
        console.log('About to update practice' + practice);
        PracticeService.updatePractice(practice, id)
          .then(
            function(response) {
              console.log('Practice updated successfully');
              self.successMessage = 'Practice updated successfully';
              self.errorMessage = '';
              self.done = true;
              self.display = false;
              $scope.myForm.$setPristine();
              self.dtInstance.reloadData();
              self.dtInstance.rerender();
            },
            function(errResponse) {
              console.error('Error while updating Practice');
              self.errorMessage = 'Error while updating Practice ' + errResponse.data;
              self.successMessage = '';
            }
          );
      }


      function removePractice(id) {
        console.log('About to remove Practice with id ' + id);
        PracticeService.removePractice(id)
          .then(
            function() {
              console.log('Practice ' + id + ' removed successfully');
              cancelEdit();
            },
            function(errResponse) {
              console.error('Error while removing practice ' + id + ', Error :' + errResponse.data);
            }
          );
      }


      function getAllPractices() {
        self.practices = PracticeService.getAllPractices();

        return self.practices;
      }


      function editPractice(id) {
        self.successMessage = '';
        self.errorMessage = '';
        PracticeService.getPractice(id).then(
          function(practice) {
            self.practice = practice;

            self.display = true;
          },
          function(errResponse) {
            console.error('Error while removing practice ' + id + ', Error :' + errResponse.data);
          }
        );
      }

      function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.practice = {};
        $scope.myForm.$setPristine(); //reset Form
      }

      function cancelEdit() {
        self.successMessage = '';
        self.errorMessage = '';
        self.practice = {};
        self.display = false;
        $state.go('main.practice', {}, {
          location: true,
          reload: false,
          notify: false
        });
      }

      function practiceEdit(id) {
    	  console.log('id',id);
        var params = {'practiceDisplay': true, 'id':id };
        var trans = $state.go('main.practice.edit', params).transition;
        trans.onSuccess({}, function() {editPractice(id);}, { practice: -1});
      }

      function addPractice() {
        self.successMessage = '';
        self.errorMessage = '';
        self.display = true;
      }

    }


  ]);
})();
