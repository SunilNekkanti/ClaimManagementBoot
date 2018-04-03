(function(){
'use strict';
var app = angular.module('my-app');

app.controller('AllocationLevelController',
    ['AllocationLevelService', '$scope', '$compile','$state','DTOptionsBuilder', 'DTColumnBuilder',  function( AllocationLevelService, $scope, $compile,$state,DTOptionsBuilder, DTColumnBuilder) {
    	
        var self = this;
        self.allocationLevel = {};
        self.allocationLevels=[];
        self.display =false;
        self.displayEditButton = false;
        self.submit = submit;
        self.addAllocationLevel = addAllocationLevel;
        self.getAllAllocationLeveles = getAllAllocationLeveles;
        self.createAllocationLevel = createAllocationLevel;
        self.updateAllocationLevel = updateAllocationLevel;
        self.removeAllocationLevel = removeAllocationLevel;
        self.editAllocationLevel = editAllocationLevel;
        self.reset = reset;
        self.cancelEdit = cancelEdit;
        self.dtInstance = {};
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
        self.dtColumns = [
            DTColumnBuilder.newColumn('description').withTitle('Description').renderWith(
					function(data, type, full,
							meta) {
						 return '<a href="javascript:void(0)" class="'+full.id+'" ng-click="ctrl.editAllocationLevel('+full.id+')">'+data+'</a>';
					}).withClass("text-left")
          ];
     
        
        self.dtOptions = DTOptionsBuilder.newOptions()
		.withOption(
				'ajax',
				{
					url : '/ClaimManagement/api/allocationLevel/',
					type : 'GET'
				}).withDataProp('data').withOption('bServerSide', true)
				.withOption("bLengthChange", false)
				.withOption("bPaginate", true)
				.withOption('bProcessing', true)
				.withOption('bStateSave', true)
		        .withDisplayLength(20).withOption( 'columnDefs', [ {
					                                orderable : false,
													className : 'select-checkbox',
													targets : 0,
													sortable : false,
													aTargets : [ 0, 1 ] } ])
				.withOption('select', {
										style : 'os',
										selector : 'td:first-child' })
			    .withOption('createdRow', createdRow)
		        .withPaginationType('full_numbers')
		        
		        .withFnServerData(serverData);

    	function serverData(sSource, aoData, fnCallback) {
			
			
			// All the parameters you need is in the aoData
			// variable
			var order = aoData[2].value;
			var page = aoData[3].value / aoData[4].value;
			var length = aoData[4].value;
			var search = aoData[5].value;

			// Then just call your service to get the
			// records from server side
			AllocationLevelService
					.loadAllocationLeveles(page, length, search.value, order)
					.then(
							function(result) {
								var records = {
									'recordsTotal' : result.data.totalElements||0,
									'recordsFiltered' : result.data.totalElements||0,
									'data' : result.data.content||{}
								};
								fnCallback(records);
							});
		}

		 function reloadData() {
			var resetPaging = false;
			self.dtInstance.reloadData(callback,
					resetPaging);
			self.dtInstance.rerender();
		} 
		 

		function callback(json) {
				console.log(json);
		} 
			
		 
       function createdRow(row, data, dataIndex) {
            // Recompiling so we can bind Angular directive to the DT
            $compile(angular.element(row).contents())($scope);
        }
        
       function checkBoxChange(checkStatus, facilityTypeId) {
			self.displayEditButton = checkStatus;
			self.facilityTypeId = facilityTypeId

		}
        function submit() {
            console.log('Submitting');
            if (self.allocationLevel.id === undefined || self.allocationLevel.id === null) {
                console.log('Saving New AllocationLevel', self.allocationLevel);
                createAllocationLevel(self.allocationLevel);
            } else {
                updateAllocationLevel(self.allocationLevel, self.allocationLevel.id);
                console.log('AllocationLevel updated with id ', self.allocationLevel.id);
            }
        }

        function createAllocationLevel(allocationLevel) {
            console.log('About to create allocationLevel');
            AllocationLevelService.createAllocationLevel(allocationLevel)
                .then(
                    function (response) {
                        console.log('AllocationLevel created successfully');
                        self.successMessage = 'AllocationLevel created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.display =false;
                        self.allocationLevel={};
                        $scope.myForm.$setPristine();
                        self.dtInstance.reloadData();
                        self.dtInstance.rerender();
                    },
                    function (errResponse) {
                        console.error('Error while creating AllocationLevel');
                        self.errorMessage = 'Error while creating AllocationLevel: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateAllocationLevel(allocationLevel, id){
            console.log('About to update allocationLevel');
            AllocationLevelService.updateAllocationLevel(allocationLevel, id)
                .then(
                    function (response){
                        console.log('AllocationLevel updated successfully');
                        self.successMessage='AllocationLevel updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.display =false;
                        $scope.myForm.$setPristine();
                        self.dtInstance.reloadData();
                        self.dtInstance.rerender();
                    },
                    function(errResponse){
                        console.error('Error while updating AllocationLevel');
                        self.errorMessage='Error while updating AllocationLevel '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeAllocationLevel(id){
            console.log('About to remove AllocationLevel with id '+id);
            AllocationLevelService.removeAllocationLevel(id)
                .then(
                    function(){
                        console.log('AllocationLevel '+id + ' removed successfully');
                        cancelEdit();
                    },
                    function(errResponse){
                        console.error('Error while removing allocationLevel '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllAllocationLeveles(){
            return AllocationLevelService.getAllAllocationLeveles();
        }

        function editAllocationLevel(id) {
            self.successMessage='';
            self.errorMessage='';
            AllocationLevelService.getAllocationLevel(id).then(
                function (allocationLevel) {
                    self.allocationLevel = allocationLevel;
                    self.display =true;
                },
                function (errResponse) {
                    console.error('Error while removing allocationLevel ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        
        function addAllocationLevel() {
            self.successMessage='';
            self.errorMessage='';
            self.display =true;
        }
        
        function allocationLevelEdit(id) {
        	var params = {'roleDisplay':true};
			var trans ;
		    trans =  $state.go('main.allocationLevel.edit',params).transition;
			
			trans.onSuccess({}, function() { editAllocationLevel(id);  });
			
        }
        
        
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.allocationLevel={};
            $scope.myForm.$setPristine(); //reset Form
        }
        
        function cancelEdit(){
            self.successMessage='';
            self.errorMessage='';
            self.allocationLevel={};
            self.display = false;
            $state.go('main.allocationLevel');
        }
    }


    ]);
   })();