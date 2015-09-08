
(function() {
    'use strict';

    angular
        .module('app')
        .controller('TimezonesController', TimezonesController)
        .controller('NewTimezonePopupController', NewTimezonePopupController);

    /** @ngInject */
    function TimezonesController(TimezonesFactory, $modal, $log, toaster) {
        var vm = this;

        vm.timezones = [];

        vm.openTimezonePopup = function (timezone) {
            var modalInstance = $modal.open({
                animation: true,
                templateUrl: 'timezonePopup.html',
                controller: 'NewTimezonePopupController',
                controllerAs: 'tzPopupCtrl',
                size: 'sm',
                resolve: {
                    timezone: function () {
                        return angular.copy(timezone);
                    }
                }
            });

            modalInstance.result.then(function (timezone) {
                activate();
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });

        };

        vm.deleteTimezone = function(timezoneIndex) {
            TimezonesFactory.delete({id:vm.timezones[timezoneIndex].id}).$promise.then(function (data) {
                vm.timezones.splice(timezoneIndex, 1);
            }, function (error) {
                toaster.pop('error', "Error", "Error deleting timezone");
                $log.error(error);
            });
        };


        activate();

        function activate() {
            TimezonesFactory.query({},
                function (data) {
                    vm.timezones = data;
                }, function (e) {
                    console.error(e);
                });
        };
    };

    /** @ngInject */
    function NewTimezonePopupController($modalInstance, TimezonesFactory, timezone, $log, toaster) {
        var vm = this;
        vm.title = timezone ? "Edit timezone" : "New Timezone";
        vm.timezone = timezone ? timezone : {};
        vm.save = function () {
            if(vm.timezone.id) {
                TimezonesFactory.update(vm.timezone).$promise.then(function (data) {
                    $modalInstance.close(data);
                }, function (error) {
                    vm.alert =  { type: 'danger', msg: 'Error udating timezone' };
                    //toaster.pop('error', "Error", "Error updating timezone");
                    $log.error(error);
                });
            } else {
                TimezonesFactory.save(vm.timezone).$promise.then(function (data) {
                    $modalInstance.close(data);
                }, function (error) {
                    vm.alert =  { type: 'danger', msg: 'Error saving timezone' };
                    //toaster.pop('error', "Error", "Error saving timezone");
                    $log.error(error);
                });
            }
        };

        vm.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        vm.closeAlert = function() {
            vm.alert =  undefined;
        }
    };
})()
