
<div class="generic-container">
   <div class="panel panel-success" ng-if="!ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="claimStatusDetail">Claim Status Detail List</span> 
               <button type="button"  ng-click="ctrl.addClaimStatusDetail()" ng-hide="ctrl.displayEditButton" class="btn btn-success custom-width floatRight"> Add </button>   
                <button type="button" ng-click="ctrl.editClaimStatusDetail(ctrl.facilityTypeId)" ng-show="ctrl.displayEditButton" class="btn btn-primary custom-width floatRight">Edit</button>  
                <button type="button" ng-click="ctrl.removeClaimStatusDetail(ctrl.facilityTypeId)"  ng-show="ctrl.displayEditButton" class="btn btn-danger custom-width floatRight">Remove</button> 
        </div>
        <div class="table-responsive">
			<div class="panel-body">
	        	<table datatable="" id="content"   dt-options="ctrl.dtOptions"  dt-columns="ctrl.dtColumns" dt-instance="ctrl.dtInstance" class="table table-hover table-responsive  bordered table-striped table-condensed datatable dt-responsive nowrap dataTable row-border hover "></table>
            </div>
		</div>
    </div>
    
    <div class="panel panel-success" ng-if="ctrl.display">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="claimStatusDetail">Claim Status Detail List</span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" claimStatusDetail="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" claimStatusDetail="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.claimStatusDetail.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">eClinical Status</label>
	                        <div class="col-md-3">
	                      <!--  <select class=" form-control" ng-model="ctrl.claimStatusDetail.description" name="description" ng-options="claimStatusDetail.description for claimStatusDetail in ctrl.claimStatusDetails | orderBy:'description' track by claimStatusDetail.description" required> -->
	                            <input type="text" ng-model="ctrl.claimStatusDetail.description" id="uname" class="username form-control input-sm" placeholder="Enter ClaimStatus name" required ng-minlength="4"/> 
	                        </div>
	                    </div>
	                    
	                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Claim Status</label>
	                        <div class="col-md-3">
	                        <select class=" form-control" ng-model="ctrl.claimStatusDetail.claimStatusId" name="claimStatusId" ng-options="claimStatus.description for claimStatus in ctrl.claimStatuss | orderBy:'description' track by claimStatus.description" required>
	                          <!--  <input type="text" ng-model="ctrl.claimStatusDetail.claimStatusId" id="uname" class="username form-control input-sm" placeholder="Enter ClaimStatus name" required ng-minlength="4"/> -->
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatCenter col-md-offset-10">
	                        <input type="submit"  value="{{!ctrl.claimStatusDetail.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-show="!ctrl.claimStatusDetail.id" ng-disabled="myForm.$pristine">Reset Form</button>
	                        <button type="button" ng-click="ctrl.cancelEdit()" class="btn btn-warning btn-sm" ng-show="ctrl.claimStatusDetail.id" >Cancel</button>
	                        <button type="button" ng-click="ctrl.removeClaimStatusDetail(ctrl.claimStatusDetail.id)" class="btn btn-warning btn-sm" ng-show="ctrl.claimStatusDetail.id" >Delete</button>
	                    
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
   
   
</div>