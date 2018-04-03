
<div class="generic-container">
   <div class="panel panel-success" ng-if="!ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="role">Role List</span> 
               <button type="button"  ng-click="ctrl.addRole()" ng-hide="ctrl.displayEditButton" class="btn btn-success custom-width floatRight"> Add </button>   
               <button type="button" ng-click="ctrl.editRole(ctrl.facilityTypeId)" ng-show="ctrl.displayEditButton" class="btn btn-primary custom-width floatRight">Edit</button>  
              <button type="button" ng-click="ctrl.removeRole(ctrl.facilityTypeId)"  ng-show="ctrl.displayEditButton" class="btn btn-danger custom-width floatRight">Remove</button>  
        </div>
        <div class="table-responsive">
			<div class="panel-body">
	        	<table datatable="" id="content"   dt-options="ctrl.dtOptions"  dt-columns="ctrl.dtColumns" dt-instance="ctrl.dtInstance" class="table table-hover table-responsive  bordered table-striped table-condensed datatable dt-responsive nowrap dataTable row-border hover "></table>
            </div>
		</div>
    </div>
    
    <div class="panel panel-success" ng-if="ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="role">Role </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.role.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Name</label>
	                        <div class="col-md-3">
	                            <input type="text" ng-model="ctrl.role.role" id="uname" class="username form-control input-sm" placeholder="Enter Role name" required ng-minlength="4"/>
	                        </div>
	                    </div>
	                </div>
	                
	                <!-- ctrl.prioritys{{ctrl.prioritys}} -->
	                 <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Priority</label>
	                        <div class="col-md-3">
	                      <!--   <select class=" form-control" ng-model="ctrl.role.priorities" name="priorities" ng-options="priority.code for priority in ctrl.prioritys | orderBy:'code' track by priority.code" required></select> -->
	                            <multiselect ng-model="ctrl.selectedPrioritys"  ng-change ="ctrl.reset()" options="ctrl.prioritys"  id-prop="id" display-prop="code" show-search="true" show-select-all="true" show-unselect-all="true"  search-limit="10"></multiselect> 
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Claim Status Details</label>
	                        <div class="col-md-3">
	                      <!--  <select class=" form-control" ng-model="ctrl.role.claimStatusDetails" name="claimStatusDetails" ng-options="claimStatusDetail.description for claimStatusDetail in ctrl.claimStatusDetails | orderBy:'description' track by claimStatusDetail.description" required></select>  -->
	                           <multiselect ng-model="ctrl.selectedClaimStatusDetails"    ng-change ="ctrl.reset()" options="ctrl.claimStatusDetails"  id-prop="id" display-prop="description" show-search="true" show-select-all="true" show-unselect-all="true"  search-limit="10"></multiselect> 
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Claim Status</label>
	                        <div class="col-md-3">
	                       <!--   <select class=" form-control" ng-model="ctrl.role.claimstatus" name="claimstatus" ng-options="claimstatus.description for claimstatus in ctrl.claimstatuss | orderBy:'description' track by claimstatus.description" required></select>  -->
	                         <multiselect ng-model="ctrl.selectedClaimStatuss"    ng-change ="ctrl.reset()" options="ctrl.claimStatuss"  id-prop="id" display-prop="description" show-search="true" show-select-all="true" show-unselect-all="true"  search-limit="10"></multiselect> 
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight col-md-offset-8">
	                        <input type="submit"  value="{{!ctrl.role.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-xs" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-xs" ng-show="!ctrl.role.id" ng-disabled="myForm.$pristine">Reset Form</button>
	                        <button type="button" ng-click="ctrl.cancelEdit()" class="btn btn-warning btn-xs" ng-show="ctrl.role.id" >Cancel</button>
	                        <button type="button" ng-click="ctrl.removeRole(ctrl.role.id)" class="btn btn-danger btn-xs" ng-show="ctrl.role.id" >Delete</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
   
   
</div>