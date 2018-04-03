  
    
<div class="generic-container" >
   <div class="panel panel-success" ng-if="!ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="target">Target List</span> 
               <button type="button"   ng-click="ctrl.addTarget()" ng-hide="ctrl.displayEditButton" class="btn btn-success  btn-xs custom-width floatRight"> Add </button>   
               <button type="button" ng-click="ctrl.editTarget(ctrl.targetId)" ng-show="ctrl.displayEditButton" class="btn btn-primary btn-xs custom-width floatRight">Edit</button>  
              <button type="button" ng-click="ctrl.removeTarget(ctrl.targetId)"  ng-show="ctrl.displayEditButton" class="btn btn-danger btn-xs custom-width floatRight">Remove</button>  
        </div>
        <div class="table-responsive">
			<div class="panel-body">
			
	        	<table datatable="" id="content"   dt-options="ctrl.dtOptions"  dt-columns="ctrl.dtColumns" dt-instance="ctrl.dtInstance" class="table-responsive table  bordered table-striped table-condensed datatable" cellspacing="0" width="100%"></table>
          </div>
		</div>
    </div>
     
    
    <div class="panel panel-success" ng-if="ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="target">Target</span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" target="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" target="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.target.id" />
	                
	                
	                  <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Name</label>
	                        <div class="col-md-3">
	                            <input type="text" ng-model="ctrl.target.description" name="description" id="uname" class="username form-control input-sm" placeholder="Enter Targert name" required ng-minlength="4"/>
	                        </div>
	                    </div>
	                     <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Count</label>
	                        <div class="col-md-3">
	                            <input type="text" ng-model="ctrl.target.targetCount" name="targetCount" id="uname" class="username form-control input-sm" placeholder=" Enter count" required ng-minlength="1"/>
		                        <div class="has-error" ng-show="myForm.$dirty">
		                          <span ng-show="myForm.targetCount.$error.required">This is a required field</span>
		                          <span ng-show="myForm.targetCount.$error.minlength">Minimum length required is 4</span>
		                          <span ng-show="myForm.targetCount.$invalid">This field is invalid </span>
		                        </div>
	                        </div>
	                    </div>
	                 <div class="row">
	                    <div class="form-actions floatRight col-md-offset-8">
	                        <input type="submit"  value="{{!ctrl.target.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-xs" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-xs" ng-show="!ctrl.target.id" ng-disabled="myForm.$pristine">Reset Form</button>
	                        <button type="button" ng-click="ctrl.cancelEdit()" class="btn btn-warning btn-xs">Cancel</button>
	                        <button type="button" ng-click="ctrl.removeTarget(ctrl.target.id)" class="btn btn-danger btn-xs" ng-show="ctrl.target.id" >Delete</button>
	                    </div>
	                </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    
    
</div>