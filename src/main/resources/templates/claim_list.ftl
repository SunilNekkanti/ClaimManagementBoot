  
    
<div class="generic-container" >
   <div class="panel panel-success" ng-if="!ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="claim">Claim List</span> 
               <button type="button"   ng-click="ctrl.addClaim()" ng-hide="ctrl.displayEditButton" class="btn btn-success  btn-xs custom-width floatRight"> Add </button>   
               <button type="button" ng-click="ctrl.editClaim(ctrl.claimId)" ng-show="ctrl.displayEditButton" class="btn btn-primary btn-xs custom-width floatRight">Edit</button>  
              <button type="button" ng-click="ctrl.removeClaim(ctrl.claimId)"  ng-show="ctrl.displayEditButton" class="btn btn-danger btn-xs custom-width floatRight">Remove</button>  
        </div>
        <div class="table-responsive">
			<div class="panel-body">
               <tabset>
                <tab  heading="My Team Assignments" ng-click="ctrl.setTeamAssignment(1)">
                	<table datatable="" id="content" dt-options="ctrl.dt1Options" dt-columns="ctrl.dt1Columns" dt-instance="ctrl.dt1Instance" dt-disable-deep-watchers="true" class="table table-hover table-responsive  bordered table-striped table-condensed datatable dt-responsive nowrap dataTable row-border hover"
        				cellspacing="0" width="100%"></table>
                </tab>
                 <tab  heading="Worked Assignments" ng-click="ctrl.setTeamAssignment(2)">
                
                </tab>
                 <tab  heading="Completed Claims" ng-click="ctrl.setTeamAssignment(3)">
                </tab>
                 <tab  heading="Allocated Oustanding" ng-click="ctrl.setTeamAssignment(4)">
                </tab>
                 <tab  heading="Unallocated Outstanding" ng-click="ctrl.setTeamAssignment(5)">
                </tab>
                 <tab  heading="Total Outstanding" ng-click="ctrl.setTeamAssignment(6)">
                </tab>
                </tablset>
                
                
        

      </div>
    </div>
  </div>

    
    <div class="panel panel-success" ng-if="ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="claim">Claim</span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" claim="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" claim="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.claim.id" />
	                
	                
	                  <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Name</label>
	                        <div class="col-md-3">
	                            <input type="text" ng-model="ctrl.claim.description" name="description" id="uname" class="username form-control input-sm" placeholder="Enter Targert name" required ng-minlength="4"/>
	                        </div>
	                    </div>
	                     <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Count</label>
	                        <div class="col-md-3">
	                            <input type="text" ng-model="ctrl.claim.claimCount" name="claimCount" id="uname" class="username form-control input-sm" placeholder=" Enter count" required ng-minlength="1"/>
		                        <div class="has-error" ng-show="myForm.$dirty">
		                          <span ng-show="myForm.claimCount.$error.required">This is a required field</span>
		                          <span ng-show="myForm.claimCount.$error.minlength">Minimum length required is 4</span>
		                          <span ng-show="myForm.claimCount.$invalid">This field is invalid </span>
		                        </div>
	                        </div>
	                    </div>
	                 <div class="row">
	                    <div class="form-actions floatRight col-md-offset-8">
	                        <input type="submit"  value="{{!ctrl.claim.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-xs" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-xs" ng-show="!ctrl.claim.id" ng-disabled="myForm.$pristine">Reset Form</button>
	                        <button type="button" ng-click="ctrl.cancelEdit()" class="btn btn-warning btn-xs">Cancel</button>
	                        <button type="button" ng-click="ctrl.removeClaim(ctrl.claim.id)" class="btn btn-danger btn-xs" ng-show="ctrl.claim.id" >Delete</button>
	                    </div>
	                </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    
    
</div>