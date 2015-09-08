(function () {
    'use strict';

    angular
        .module('app')
        .factory('TimezonesFactory', TimezonesFactory);

    TimezonesFactory
        .$inject = [
        '$resource'
    ];

    function TimezonesFactory($resource) {
        return $resource('/api/timezones/:id', { id: '@id' }, {
            'query': {
                method: 'GET',
                isArray: true
            },
            'update': {
                method: 'PUT'
            }
        });
    }
})();
