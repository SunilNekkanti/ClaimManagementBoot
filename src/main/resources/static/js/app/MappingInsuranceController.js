(function(){
'use strict';
var app = angular.module('my-app');

app.controller('MappingInsuranceController',
    ['MappingInsuranceService','InsuranceService','PlanTypeService', 'StateService','FileUploadService', '$sce', '$scope',  '$compile','$state','$stateParams','DTOptionsBuilder', 'DTColumnBuilder', function( MappingInsuranceService , InsuranceService ,  PlanTypeService,  StateService, FileUploadService, $sce, $scope, $compile, $state, $stateParams,DTOptionsBuilder, DTColumnBuilder) {

        var self = this;
        self.mappingInsurance = {};
        self.mappingInsurances=[];
        self.insurances=[];
        self.display =$stateParams.mappingInsuranceDisplay||false;
        self.displayEditButton = false;
        self.submit = submit;
        self.planTypes=[];
        self.states=[];
        self.insurances = getAllInsurances();
        self.getAllInsurances = getAllInsurances;
        self.getAllMappingInsurances = getAllMappingInsurances;
        self.createMappingInsurance = createMappingInsurance;
        self.updateMappingInsurance = updateMappingInsurance;
        self.removeMappingInsurance = removeMappingInsurance;
        self.editMappingInsurance = editMappingInsurance;
        self.addMappingInsurance = addMappingInsurance;
        self.getAllPlanTypes = getAllPlanTypes;
        self.getAllStates = getAllStates;
        self.dtInstance = {};
		self.mappingInsuranceId = null;
        self.reset = reset;
        self.mappingInsuranceEdit = mappingInsuranceEdit;
        self.cancelEdit = cancelEdit;
        self.readUploadedFile = readUploadedFile;
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
        self.checkBoxChange = checkBoxChange;
        self.currentScreen = $state.current.data.currentScreen;
        self.toScreen = $state.current.data.toScreen;
        self.linkToScreen = $state.current.data.linkToScreen;
                
        self.dtColumns = [
            DTColumnBuilder.newColumn('name').withTitle('Mapping Insurance').renderWith(
					function(data, type, full,
							meta) {
						 return '<a href="javascript:void(0)" class="'+full.id+'" ng-click="ctrl.mappingInsuranceEdit('+full.id+')">'+data+'</a>';
					}).withClass("text-left"),
            DTColumnBuilder.newColumn('insId.name').withTitle('Master Insurance')
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
		.withOption('order', [[0,'ASC']])
		.withOption('aLengthMenu', [[15, 20, -1],[ 15, 20, "All"]])
		.withOption('bDeferRender', true)
		.withFnServerData(serverData);

    	function serverData(sSource, aoData, fnCallback) {
			
			
			// All the parameters you need is in the aoData
			// variable
			var order = aoData[2].value;
			var page = aoData[3].value / aoData[4].value ;
			var length = aoData[4].value ;
			var search = aoData[5].value;

			var paramMap = {};
			for ( var i = 0; i < aoData.length; i++) {
			  paramMap[aoData[i].name] = aoData[i].value;
			}
			
			var sortCol ='';
			var sortDir ='';
			// extract sort information
			 if(paramMap['columns'] !== undefined && paramMap['columns'] !== null && paramMap['order'] !== undefined && paramMap['order'] !== null ){
				 sortCol = paramMap['columns'][paramMap['order'][0]['column']].data;
				  sortDir = paramMap['order'][0]['dir'];
			 }
			 
			// Then just call your service to get the
			// records from server side
			MappingInsuranceService
					.loadMappingInsurances(page, length, search.value, sortCol+','+sortDir, self.currentScreen)
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
		} 
		 
       function createdRow(row, data, dataIndex) {
            // Recompiling so we can bind Angular directive to the DT
            $compile(angular.element(row).contents())($scope);
        }
        
       function checkBoxChange(checkStatus, mappingInsuranceId) {
			self.displayEditButton = checkStatus;
			self.mappingInsuranceId = mappingInsuranceId

		}
       
       
        function submit() {
            console.log('Submitting');
            if(self.myFile){
				var  promise = FileUploadService.uploadContractFileToUrl(self.myFile);

	            promise.then(function (response) {
	            	if(!self.mappingInsurance.contracts[0].fileUpload){
	            		self.mappingInsurance.contract[0].fileUpload = {};
	            	}
	            	 if(response.length >0 )
	                self.mappingInsurance.contracts[0].fileUpload= response[0];
	            	 if (self.mappingInsurance.id === undefined || self.mappingInsurance.id === null) {
							console.log('Saving New MappingInsurance');
							createMappingInsurance(self.mappingInsurance);
							
						} else {
							updateMappingInsurance(self.mappingInsurance, self.mappingInsurance.id)	 
							console.log('MappingInsurance updated with id ',
									self.mappingInsurance.id);
						}
						self.displayEditButton = false;
	                
	            }, function () {
	                self.serverResponse = 'An error has occurred';
	            });
			}else{
				if (self.mappingInsurance.id === undefined || self.mappingInsurance.id === null) {
					console.log('Saving New MappingInsurance');
					createMappingInsurance(self.mappingInsurance);
					
				} else {
					updateMappingInsurance(self.mappingInsurance, self.mappingInsurance.id)	 
					console.log('MappingInsurance updated with id ',
							self.mappingInsurance.id);
				}
				self.displayEditButton = false;
			}
        }

        function createMappingInsurance(mappingInsurance) {
            console.log('About to create mappingInsurance');
            MappingInsuranceService.createMappingInsurance(mappingInsurance)
                .then(
                    function (response) {
                        console.log('MappingInsurance created successfully');
                        self.successMessage = 'MappingInsurance created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.display =false;
                        self.mappingInsurances = getAllMappingInsurances();
                        self.mappingInsurance={};
                        cancelEdit();
                    },
                    function (errResponse) {
                        console.error('Error while creating MappingInsurance');
                        self.errorMessage = 'Error while creating MappingInsurance: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateMappingInsurance(mappingInsurance, id){
            console.log('About to update mappingInsurance'+mappingInsurance);
            MappingInsuranceService.updateMappingInsurance(mappingInsurance, id)
                .then(
                    function (response){
                        console.log('MappingInsurance updated successfully');
                        self.successMessage='MappingInsurance updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.display =false;
                        $scope.myForm.$setPristine();
                        self.dtInstance.reloadData();
                        self.dtInstance.rerender();
                    },
                    function(errResponse){
                        console.error('Error while updating MappingInsurance');
                        self.errorMessage='Error while updating MappingInsurance '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeMappingInsurance(id){
            console.log('About to remove MappingInsurance with id '+id);
            MappingInsuranceService.removeMappingInsurance(id)
                .then(
                    function(){
                        console.log('MappingInsurance '+id + ' removed successfully');
                        cancelEdit();
                    },
                    function(errResponse){
                        console.error('Error while removing mappingInsurance '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllMappingInsurances(){
        	return  MappingInsuranceService.getAllMappingInsurances();
        }
        
        
        function getAllPlanTypes(){
            self.planTypes = PlanTypeService.getAllPlanTypes();
  
           return self.planTypes;
       }
       
        function getAllInsurances(){
        	 self.insurances = InsuranceService.getAllInsurances();
             console.log('self.insurances',self.insurances);
        	return  self.insurances;
        }
        function getAllStates() {
			return StateService.getAllStates();
		}
        
        function editMappingInsurance(id) {
            self.successMessage='';
            self.errorMessage='';
            self.planTypes = getAllPlanTypes();
            self.states = getAllStates();
            MappingInsuranceService.getMappingInsurance(id).then(
                function (mappingInsurance) {
                    self.mappingInsurance = mappingInsurance;
                    if(!self.mappingInsurance.contracts || self.mappingInsurance.contracts.length == 0){
                       self.mappingInsurance.contracts = [];
	                   self.mappingInsurance.contracts.push({});
                    }
                    
                    self.display = true;
                },
                function (errResponse) {
                    console.error('Error while removing mappingInsurance ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.mappingInsurance={};
            $scope.myForm.$setPristine(); //reset Form
        }
       
        function cancelEdit(){
            self.successMessage='';
            self.errorMessage='';
            self.mappingInsurance={};
            self.display = false;
            if(self.currentScreen === 'Active') {
           		 $state.go('main.mappingInsurance', {}, {location: true,reload: false,notify: false});
            }else{
            	$state.go('main.mappingInsuranceArchives', {}, {location: true,reload: false,notify: false});
            }
            
        }
        
        function mappingInsuranceEdit(id) {
        	var params = {'mappingInsuranceDisplay':true};
        	var trans;
        	if(self.currentScreen === 'Active') {
				trans =  $state.go('main.mappingInsurance.edit',params).transition;
			}else {
			    trans =  $state.go('main.mappingInsuranceArchives.edit',params).transition;
			}
			trans.onSuccess({}, function() { editMappingInsurance(id);  }, { priority: -1 });
			
        }
        
        function addMappingInsurance() {
        	var params = {'mappingInsuranceDisplay':true};
			var trans =  $state.go('main.mappingInsurance.edit',params).transition;
			trans.onSuccess({}, function() { 
				self.successMessage='';
	            self.errorMessage='';
	            self.mappingInsurance = {};
	            self.mappingInsurance.contracts = [];
	            self.mappingInsurance.contracts.push({});
	            self.planTypes = getAllPlanTypes();
	            self.states = getAllStates();
	            self.display =true; 
				}, { priority: -1 });
            
        }
        
        function readUploadedFile(){
			console.log('About to read consignment form');
			FileUploadService.getFileUpload(self.mappingInsurance.contracts[0].fileUpload.id).then(
					function(response) {
						self.errorMessage = '';
						var file = new Blob([response], {type: self.mappingInsurance.contracts[0].fileUpload.contentType});
						 var fileURL = URL.createObjectURL(file);
					    self.content = $sce.trustAsResourceUrl(fileURL); 
					    
					},
					function(errResponse) {
						console
								.error('Error while reading consignment form');
						self.errorMessage = 'Error while reading consignment form: '
								+ errResponse.data.errorMessage;
						self.successMessage = '';
					}); 
		}
    
    }
    

    ]);
   })();