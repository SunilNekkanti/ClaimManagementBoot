
<div class="generic-container">
   <div class="panel panel-success" ng-if="!ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="allocation">Allocation List</span> 
               <button type="button"  ng-click="ctrl.addAllocation()" ng-hide="ctrl.displayEditButton" class="btn btn-success custom-width floatRight"> Add </button>   
               <button type="button" ng-click="ctrl.editAllocation(ctrl.allocationId)" ng-show="ctrl.displayEditButton" class="btn btn-primary custom-width floatRight">Edit</button>  
              <button type="button" ng-click="ctrl.removeallocation(ctrl.allocationId)"  ng-show="ctrl.displayEditButton" class="btn btn-danger custom-width floatRight">Remove</button>  
        </div>
        <div class="table-responsive">
			<div class="panel-body">
	        	<table datatable="" id="content"   dt-options="ctrl.dtOptions"  dt-columns="ctrl.dtColumns" dt-instance="ctrl.dtInstance" dt-disable-deep-watchers="true" class="table-responsive table  bordered table-striped table-condensed datatable" cellspacing="0" width="100%"></table>
            </div>
		</div>
    </div>
    
    <div class="panel panel-success" ng-if="ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="allocation">Allocation Edit</span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" allocation="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" allocation="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.allocation.id" />
	                
	                
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Priority</label>
	                        <div class="col-md-3">
	                            <input type="text" ng-model="ctrl.allocation.priority.description" id="uname" class="username form-control input-sm" placeholder="Enter priority name" required ng-minlength="4" />
	                        </div>
	                    </div>
	                </div>
	                
	                 <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Allocation Level</label>
	                        <div class="col-md-3">
	                        
	                        <select class=" form-control" ng-model="ctrl.allocation.allocLevel" name="allocLevel" ng-options="allocationLevel.description for allocationLevel in ctrl.allocationLevels | orderBy:'description' track by allocationLevel.description" required>
	                              <option> </option>
	                            </select>  
	                            
	                            
	                        </div>
	                    </div>
	                </div>
	                
	                 <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Percentage</label>
	                        <div class="col-md-3">
	                            <input type="text" ng-model="ctrl.allocation.percentage" id="uname" class="username form-control input-sm" placeholder="Enter Percentage" required ng-minlength="1"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight col-md-offset-8">
	                        <input type="submit"  value="{{!ctrl.allocation.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-xs" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-xs" ng-show="!ctrl.allocation.id" ng-disabled="myForm.$pristine">Reset Form</button>
	                        <button type="button" ng-click="ctrl.cancelEdit()" class="btn btn-warning btn-xs">Cancel</button>
	                    <button type="button" ng-click="ctrl.removeAllocation(ctrl.allocation.id)" class="btn btn-danger btn-xs" ng-show="ctrl.allocation.id" >Delete</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
   
   
</div>