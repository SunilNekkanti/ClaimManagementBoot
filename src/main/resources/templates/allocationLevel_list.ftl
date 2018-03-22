
<div class="generic-container">
   <div class="panel panel-success" ng-if="!ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="allocationLevel">Allocation Levels</span> 
               <button type="button"  ng-click="ctrl.addAllocationLevel()" ng-hide="ctrl.displayEditButton" class="btn btn-success custom-width floatRight"> Add </button>   
               <button type="button" ng-click="ctrl.editAllocationLevel(ctrl.facilityTypeId)" ng-show="ctrl.displayEditButton" class="btn btn-primary custom-width floatRight">Edit</button>  
              <button type="button" ng-click="ctrl.removeAllocationLevel(ctrl.facilityTypeId)"  ng-show="ctrl.displayEditButton" class="btn btn-danger custom-width floatRight">Remove</button>  
        </div>
        <div class="table-responsive">
			<div class="panel-body">
	        	<table datatable="" id="content"   dt-options="ctrl.dtOptions"  dt-columns="ctrl.dtColumns" dt-instance="ctrl.dtInstance" class="table table-hover table-responsive  bordered table-striped table-condensed datatable dt-responsive nowrap dataTable row-border hover "></table>
            </div>
		</div>
    </div>
    
    <div class="panel panel-success" ng-if="ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="allocationLevel">Allocation Level Edit</span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" allocationLevel="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" allocationLevel="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.allocationLevel.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Name</label>
	                        <div class="col-md-3">
	                            <input type="text" ng-model="ctrl.allocationLevel.description" id="uname" class="username form-control input-sm" placeholder="Enter Allocation Level name" required ng-minlength="4"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatCenter col-md-offset-8">
	                        <input type="submit"  value="{{!ctrl.allocationLevel.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-show="!ctrl.allocationLevel.id" ng-disabled="myForm.$pristine">Reset Form</button>
	                        <button type="button" ng-click="ctrl.cancelEdit()" class="btn btn-warning btn-sm" ng-show="ctrl.allocationLevel.id" >Cancel</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
   
   
</div>