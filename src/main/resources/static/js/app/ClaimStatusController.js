(function(){
'use strict';
var app = angular.module('my-app');

app.controller('ClaimStatusController',
    ['ClaimStatusService', '$scope', '$compile','$state','DTOptionsBuilder', 'DTColumnBuilder',  function( ClaimStatusService, $scope, $compile,$state,DTOptionsBuilder, DTColumnBuilder) {
    	
        var self = this;
        self.claimstatus = {};
        self.claimstatuss=[];
        self.display =false;
        self.displayEditButton = false;
        self.submit = submit;
        self.addClaimStatus = addClaimStatus;
        self.getAllClaimStatuses = getAllClaimStatuses;
        self.createClaimStatus = createClaimStatus;
        self.updateClaimStatus = updateClaimStatus;
        self.removeClaimStatus = removeClaimStatus;
        self.editClaimStatus = editClaimStatus;
        self.reset = reset;
        self.cancelEdit = cancelEdit;
        self.dtInstance = {};
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
        self.dtColumns = [
            DTColumnBuilder.newColumn('description').withTitle('CLAIM_STATUS').renderWith(
					function(data, type, full,
							meta) {
						 return '<a href="javascript:void(0)" class="'+full.id+'" ng-click="ctrl.editClaimStatus('+full.id+')">'+data+'</a>';
					}).withClass("text-left")
          ];
     
        
        self.dtOptions = DTOptionsBuilder.newOptions()
		.withOption(
				'ajax',
				{
					url : '/ClaimManagement/api/claimstatus/',
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
			ClaimStatusService
					.loadClaimStatuses(page, length, search.value, order)
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
            if (self.claimstatus.id === undefined || self.claimstatus.id === null) {
                console.log('Saving New ClaimStatus', self.claimstatus);
                createClaimStatus(self.claimstatus);
            } else {
                updateClaimStatus(self.claimstatus, self.claimstatus.id);
                console.log('ClaimStatus updated with id ', self.claimstatus.id);
            }
        }

        function createClaimStatus(claimstatus) {
            console.log('About to create claimstatus');
            ClaimStatusService.createClaimStatus(claimstatus)
                .then(
                    function (response) {
                        console.log('ClaimStatus created successfully');
                        self.successMessage = 'ClaimStatus created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.display =false;
                        self.claimstatus={};
                        $scope.myForm.$setPristine();
                        self.dtInstance.reloadData();
                        self.dtInstance.rerender();
                    },
                    function (errResponse) {
                        console.error('Error while creating ClaimStatus');
                        self.errorMessage = 'Error while creating ClaimStatus: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateClaimStatus(claimstatus, id){
            console.log('About to update claimstatus');
            ClaimStatusService.updateClaimStatus(claimstatus, id)
                .then(
                    function (response){
                        console.log('ClaimStatus updated successfully');
                        self.successMessage='ClaimStatus updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.display =false;
                        $scope.myForm.$setPristine();
                        self.dtInstance.reloadData();
                        self.dtInstance.rerender();
                    },
                    function(errResponse){
                        console.error('Error while updating ClaimStatus');
                        self.errorMessage='Error while updating ClaimStatus '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeClaimStatus(id){
            console.log('About to remove ClaimStatus with id '+id);
            ClaimStatusService.removeClaimStatus(id)
                .then(
                    function(){
                        console.log('ClaimStatus '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing claimstatus '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllClaimStatuses(){
            return ClaimStatusService.getAllClaimStatuses();
        }

        function editClaimStatus(id) {
            self.successMessage='';
            self.errorMessage='';
            ClaimStatusService.getClaimStatus(id).then(
                function (claimstatus) {
                    self.claimstatus = claimstatus;
                    self.display =true;
                },
                function (errResponse) {
                    console.error('Error while removing claimstatus ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        
        function addClaimStatus() {
            self.successMessage='';
            self.errorMessage='';
            self.display =true;
        }
        
        
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.claimstatus={};
            $scope.myForm.$setPristine(); //reset Form
        }
        
        function cancelEdit(){
            self.successMessage='';
            self.errorMessage='';
            self.claimstatus={};
            self.display = false;
            $state.go('main.claimstatus');
        }
    }


    ]);
   })();