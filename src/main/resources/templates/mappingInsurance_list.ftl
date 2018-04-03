<div class="generic-container">
  <div class="panel panel-success" ng-if="!ctrl.display">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="mappingInsurance">Mapping Insurance List</span>
      <button type="button" ng-click="ctrl.addMappingInsurance()" ng-hide="ctrl.displayEditButton" class="btn btn-success btn-xs  custom-width floatRight"> Add </button>
      <button type="button" ng-click="ctrl.editMappingInsurance(ctrl.mappingInsuranceId)" ng-show="ctrl.displayEditButton" class="btn btn-primary custom-width floatRight">Edit</button>
      <button type="button" ng-click="ctrl.removeMappingInsurance(ctrl.mappingInsuranceId)" ng-show="ctrl.displayEditButton" class="btn btn-danger custom-width floatRight">Remove</button>
       
    </div>
    <div class="table-responsive">
      <div class="panel-body">

        <table datatable="" id="content" dt-options="ctrl.dtOptions" dt-columns="ctrl.dtColumns" dt-instance="ctrl.dtInstance" dt-disable-deep-watchers="true" class="table-responsive table  bordered table-striped table-condensed datatable" cellspacing="0" width="100%"></table>
      </div>
    </div>
  </div>


  <div class="panel panel-success" ng-if="ctrl.display">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">Mapping Insurance Profile</span></div>
    <div class="panel-body">
      <div class="formcontainer">
        <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
        <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
        <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
          <input type="hidden" ng-model="ctrl.mappingInsurance.id" />


                  <div class="row">
                    <div class="form-group col-md-12">
                      <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Mapping Insurance</label>
                      <div class="col-md-3">
                        <input type="text" ng-model="ctrl.mappingInsurance.name" id="uname" class="username form-control input-sm" placeholder="Enter mapping insurance name" required ng-minlength="4" />
                        
                      </div>
                    </div>
                    
                    <div class="form-group col-md-12">
	                        <label class="col-md-1 col-md-offset-4 control-lable" for="uname">Master Insurance</label>
	                        <div class="col-md-3">
	                         <select class=" form-control" ng-model="ctrl.mappingInsurance.insId" name="insId" ng-options="insurance.name for insurance in ctrl.insurances | orderBy:'name' track by insurance.name" required>
	                         <!--   <input type="text" ng-model="ctrl.mappingInsurance.insId.name" id="uname" class="username form-control input-sm" placeholder=" Enter count" required ng-minlength="1"/> -->
	                        </div>
	                    </div>


                      
          <div class="row">
            <div class="form-actions floatRight col-md-offset-8">
              <input type="submit" value="{{!ctrl.mappingInsurance.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-xs" ng-disabled="myForm.$invalid || myForm.$pristine">
              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-xs" ng-show="!ctrl.mappingInsurance.id" ng-disabled="myForm.$pristine">Reset Form</button>
	          <button type="button" ng-click="ctrl.cancelEdit()" class="btn btn-warning btn-xs">Cancel</button>
              <button type="button" ng-click="ctrl.removeMappingInsurance(ctrl.mappingInsurance.id)" class="btn btn-danger btn-xs" ng-show="ctrl.mappingInsurance.id" >Delete</button>
            </div>
          </div>
          </div>
        </form>
      </div>
    </div>
  </div>


</div>