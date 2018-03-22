(function(){
'use strict';
var app = angular.module('my-app');

app.controller('AllocationController',
    ['AllocationService','PriorityService','AllocationLevelService', '$scope', '$compile','$state','DTOptionsBuilder', 'DTColumnBuilder',  function( AllocationService,PriorityService,AllocationLevelService, $scope, $compile,$state,DTOptionsBuilder, DTColumnBuilder) {
    	
        var self = this;
        self.allocation = {};
        self.allocations=[];
        self.prioritys = [];
        self.allocationLevels=[];
        self.display =false;
        self.displayEditButton = false;
        self.submit = submit;
        self.addAllocation = addAllocation;
        self.getAllPrioritys = getAllPrioritys;
        self.getAllAllocationLeveles = getAllAllocationLeveles;
        self.getAllAllocationes = getAllAllocationes;
        self.createAllocation = createAllocation;
        self.updateAllocation = updateAllocation;
        self.removeAllocation = removeAllocation;
        self.editAllocation = editAllocation;
        self.reset = reset;
        self.cancelEdit = cancelEdit;
        self.dtInstance = {};
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
        self.allocationLevels = getAllAllocationLeveles();
        self.prioritys = getAllPrioritys();
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
        self.dtColumns = [
            DTColumnBuilder.newColumn('priority.description').withTitle('Priority').renderWith(
					function(data, type, full,
							meta) {
						 return '<a href="javascript:void(0)" class="'+full.id+'" ng-click="ctrl.editAllocation('+full.id+')">'+data+'</a>';
					}).withClass("text-left"),
					DTColumnBuilder.newColumn('allocLevel.description').withTitle('Allocation Level'),
					DTColumnBuilder.newColumn('percentage').withTitle('Percentage')
          ];
     
        
        self.dtOptions = DTOptionsBuilder.newOptions()
		.withOption(
				'ajax',
				{
					url : '/ClaimManagement/api/allocation/',
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
			AllocationService
					.loadAllocationes(page, length, search.value, order)
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
            if (self.allocation.id === undefined || self.allocation.id === null) {
                console.log('Saving New Allocation', self.allocation);
                createAllocation(self.allocation);
            } else {
                updateAllocation(self.allocation, self.allocation.id);
                console.log('Allocation updated with id ', self.allocation.id);
            }
        }

        function createAllocation(allocation) {
            console.log('About to create allocation');
            AllocationService.createAllocation(allocation)
                .then(
                    function (response) {
                        console.log('Allocation created successfully');
                        self.successMessage = 'Allocation created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.display =false;
                        self.allocation={};
                        $scope.myForm.$setPristine();
                        self.dtInstance.reloadData();
                        self.dtInstance.rerender();
                    },
                    function (errResponse) {
                        console.error('Error while creating Allocation');
                        self.errorMessage = 'Error while creating Allocation: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateAllocation(allocation, id){
        	console.log('About to update allocation'+allocation);
            console.log('About to update allocation');
            AllocationService.updateAllocation(allocation, id)
                .then(
                    function (response){
                        console.log('Allocation updated successfully');
                        self.successMessage='Allocation updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.display =false;
                        $scope.myForm.$setPristine();
                        self.dtInstance.reloadData();
                        self.dtInstance.rerender();
                    },
                    function(errResponse){
                        console.error('Error while updating Allocation');
                        self.errorMessage='Error while updating Allocation '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeAllocation(id){
            console.log('About to remove Allocation with id '+id);
            AllocationService.removeAllocation(id)
                .then(
                    function(){
                        console.log('Allocation '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing allocation '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllAllocationes(){
            return AllocationService.getAllAllocationes();
        }
        
        function getAllPrioritys() {
            self.prioritys = PriorityService.getAllPrioritys();
            console.log('self.prioritys',self.prioritys);
            return self.prioritys;
          }
        function getAllAllocationLeveles(){
        	self.allocationLeveles = AllocationLevelService.getAllAllocationLeveles();
            console.log('self.allocationLevels',self.allocationLeveles);
            return self.allocationLeveles;
        }

        function editAllocation(id) {
            self.successMessage='';
            self.errorMessage='';
            AllocationService.getAllocation(id).then(
                function (allocation) {
                    self.allocation = allocation;
                    self.display =true;
                },
                function (errResponse) {
                    console.error('Error while removing allocation ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        
        function addAllocation() {
        	
				self.successMessage='';
	            self.errorMessage='';
	            self.display =true; 
			
        }
				

        
        function allocationEdit(id) {
        	var params = {'allocationDisplay':true};
			var trans ;
			    trans =  $state.go('main.allocation.edit',params).transition;
			trans.onSuccess({}, function() { editAllocation(id);  }, { priority}, { AllocationLevel : -1},{percentage: -1});
        }
        
        
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.allocation={};
            $scope.myForm.$setPristine(); //reset Form
        }
        
        function cancelEdit(){
            self.successMessage='';
            self.errorMessage='';
            self.allocation={};
            self.display = false;
            $state.go('main.allocation');
        }
    }


    ]);
   })();