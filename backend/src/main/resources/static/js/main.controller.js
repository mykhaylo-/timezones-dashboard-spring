(function() {
    'use strict';

    angular.module('app', [
        'ngResource', 'ui.bootstrap', 'toaster'
    ]).config(httpHeaderConfig);

    /** @ngInject */
    function httpHeaderConfig($httpProvider) {
        $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN'
    };
})();
