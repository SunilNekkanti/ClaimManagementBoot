  
    
<div class="generic-container" >
   <div class="panel panel-success" ng-if="!ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="providerInsuranceDetails">ProviderInsuranceDetails List</span> 
               <button type="button"   ng-click="ctrl.addProviderInsuranceDetails()" ng-hide="ctrl.displayEditButton" class="btn btn-success  btn-xs custom-width floatRight"> Add </button>   
               <button type="button" ng-click="ctrl.editProviderInsuranceDetails(ctrl.providerInsuranceDetailsId)" ng-show="ctrl.displayEditButton" class="btn btn-primary btn-xs custom-width floatRight">Edit</button>  
              <button type="button" ng-click="ctrl.removeProviderInsuranceDetails(ctrl.providerInsuranceDetailsId)"  ng-show="ctrl.displayEditButton" class="btn btn-danger btn-xs custom-width floatRight">Remove</button>  
        </div>
        <div class="table-responsive">
			<div class="panel-body">
			
	        	<table datatable="" id="content"   dt-options="ctrl.dtOptions"  dt-columns="ctrl.dtColumns" dt-instance="ctrl.dtInstance" class="table-responsive table  bordered table-striped table-condensed datatable" cellspacing="0" width="100%"></table>
          </div>
		</div>
    </div>
     
    
    <div class="panel panel-success" ng-if="ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="providerInsuranceDetails">ProviderInsuranceDetails</span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" providerInsuranceDetails="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" providerInsuranceDetails="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.providerInsuranceDetails.id" />
	                
	                
	                  <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Name</label>
	                        <div class="col-md-3">
	                            <input type="text" ng-model="ctrl.providerInsuranceDetails.description" name="description" id="uname" class="username form-control input-sm" placeholder="Enter Targert name" required ng-minlength="4"/>
	                        </div>
	                    </div>
	                     <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Count</label>
	                        <div class="col-md-3">
	                            <input type="text" ng-model="ctrl.providerInsuranceDetails.providerInsuranceDetailsCount" name="providerInsuranceDetailsCount" id="uname" class="username form-control input-sm" placeholder=" Enter count" required ng-minlength="1"/>
		                        <div class="has-error" ng-show="myForm.$dirty">
		                          <span ng-show="myForm.providerInsuranceDetailsCount.$error.required">This is a required field</span>
		                          <span ng-show="myForm.providerInsuranceDetailsCount.$error.minlength">Minimum length required is 4</span>
		                          <span ng-show="myForm.providerInsuranceDetailsCount.$invalid">This field is invalid </span>
		                        </div>
	                        </div>
	                    </div>
	                 <div class="row">
	                    <div class="form-actions floatRight col-md-offset-8">
	                        <input type="submit"  value="{{!ctrl.providerInsuranceDetails.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-xs" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-xs" ng-show="!ctrl.providerInsuranceDetails.id" ng-disabled="myForm.$pristine">Reset Form</button>
	                        <button type="button" ng-click="ctrl.cancelEdit()" class="btn btn-warning btn-xs">Cancel</button>
	                        <button type="button" ng-click="ctrl.removeProviderInsuranceDetails(ctrl.providerInsuranceDetails.id)" class="btn btn-danger btn-xs" ng-show="ctrl.providerInsuranceDetails.id" >Delete</button>
	                    </div>
	                </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    
    
</div>