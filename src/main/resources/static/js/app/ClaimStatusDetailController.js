(function(){
'use strict';
var app = angular.module('my-app');

app.controller('ClaimStatusDetailController',
    ['ClaimStatusDetailService','ClaimStatusService', '$scope', '$compile','$state','DTOptionsBuilder', 'DTColumnBuilder',  function( ClaimStatusDetailService,ClaimStatusService, $scope, $compile,$state,DTOptionsBuilder, DTColumnBuilder) {
    	
        var self = this;
        self.claimStatusDetails=[];
        self.claimStatuss=[];
        self.display =false;
        self.displayEditButton = false;
        self.submit = submit;
        self.addClaimStatusDetail = addClaimStatusDetail;
        self.getAllClaimStatusDetailes = getAllClaimStatusDetailes;
        self.getAllClaimStatuses = getAllClaimStatuses;
        self.createClaimStatusDetail = createClaimStatusDetail;
        self.updateClaimStatusDetail = updateClaimStatusDetail;
        self.removeClaimStatusDetail = removeClaimStatusDetail;
        self.editClaimStatusDetail = editClaimStatusDetail;
        self.claimStatusDetailEdit = claimStatusDetailEdit;
        self.reset = reset;
        self.cancelEdit = cancelEdit;
        self.dtInstance = {};
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
        self.claimStatuss = getAllClaimStatuses();

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
        self.dtColumns = [
            DTColumnBuilder.newColumn('description').withTitle('eClinical Status Name').renderWith(
					function(data, type, full,
							meta) {
						 return '<a href="javascript:void(0)" class="'+full.id+'" ng-click="ctrl.claimStatusDetailEdit('+full.id+')">'+data+'</a>';
					}).withClass("text-left"),
					DTColumnBuilder.newColumn('claimStatusId.description').withTitle('System Claim Status')
					
          ];
     
        
        self.dtOptions = DTOptionsBuilder.newOptions()
		.withOption(
				'ajax',
				{
					url : '/ClaimManagement/api/claimStatusDetail/',
					type : 'GET'
				}).withDataProp('data').withOption('bServerSide', true)
				.withOption("bLengthChange", false)
				.withOption("bPaginate", true)
				.withOption('bProcessing', true)
				.withOption('bStateSave', true)
		        .withDisplayLength(20)
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
			ClaimStatusDetailService
					.loadClaimStatusDetailes(page, length, search.value, order)
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
            if (self.claimStatusDetail.id === undefined || self.claimStatusDetail.id === null) {
                console.log('Saving New ClaimStatusDetail', self.claimStatusDetail);
                createClaimStatusDetail(self.claimStatusDetail);
            } else {
                updateClaimStatusDetail(self.claimStatusDetail, self.claimStatusDetail.id);
                console.log('ClaimStatusDetail updated with id ', self.claimStatusDetail.id);
            }
        }

        function createClaimStatusDetail(claimStatusDetail) {
            console.log('About to create claimStatusDetail');
            ClaimStatusDetailService.createClaimStatusDetail(claimStatusDetail)
                .then(
                    function (response) {
                        console.log('ClaimStatusDetail created successfully');
                        self.successMessage = 'ClaimStatusDetail created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.display =false;
                        self.claimStatusDetail={};
                        $scope.myForm.$setPristine();
                        self.dtInstance.reloadData();
                        self.dtInstance.rerender();
                    },
                    function (errResponse) {
                        console.error('Error while creating ClaimStatusDetail');
                        self.errorMessage = 'Error while creating ClaimStatusDetail: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateClaimStatusDetail(claimStatusDetail, id){
            console.log('About to update claimStatusDetail');
            ClaimStatusDetailService.updateClaimStatusDetail(claimStatusDetail, id)
                .then(
                    function (response){
                        console.log('ClaimStatusDetail updated successfully');
                        self.successMessage='ClaimStatusDetail updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.display =false;
                        $scope.myForm.$setPristine();
                        self.dtInstance.reloadData();
                        self.dtInstance.rerender();
                    },
                    function(errResponse){
                        console.error('Error while updating ClaimStatusDetail');
                        self.errorMessage='Error while updating ClaimStatusDetail '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeClaimStatusDetail(id){
            console.log('About to remove ClaimStatusDetail with id '+id);
            ClaimStatusDetailService.removeClaimStatusDetail(id)
                .then(
                    function(){
                        console.log('ClaimStatusDetail '+id + ' removed successfully');
                        cancelEdit();
                    },
                    function(errResponse){
                        console.error('Error while removing claimStatusDetail '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllClaimStatusDetailes(){
            return ClaimStatusDetailService.getAllClaimStatusDetailes();
        }
        
        function getAllClaimStatuses(){
        	self.claimStatuses = ClaimStatusService.getAllClaimStatuses();
            console.log('self.claimStatuss',self.claimStatuses);
            return self.claimStatuses;
        }

        function editClaimStatusDetail(id) {
            self.successMessage='';
            self.errorMessage='';
            ClaimStatusDetailService.getClaimStatusDetail(id).then(
                function (claimStatusDetail) {
                    self.claimStatusDetail = claimStatusDetail;
                    self.display =true;
                },
                function (errResponse) {
                    console.error('Error while removing claimStatusDetail ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        
        function claimStatusDetailEdit(id) {
        	console.log('id',id);
        	var params = {'claimStatusDetailDisplay':true, 'id':id};
        	var trans  =  $state.go('main.claimStatusDetail.edit',params).transition;
        	
			trans.onSuccess({}, function() { editClaimStatusDetail(id);  }, { priority: -1 });
			
        }
        
        function addClaimStatusDetail() {
            self.successMessage='';
            self.errorMessage='';
            self.display =true;
        }
        
        
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.claimStatusDetail={};
            $scope.myForm.$setPristine(); //reset Form
        }
        
        function cancelEdit(){
            self.successMessage='';
            self.errorMessage='';
            self.claimStatusDetail={};
            self.display = false;
            $state.go('main.claimStatusDetail');
        }
    }


    ]);
   })();