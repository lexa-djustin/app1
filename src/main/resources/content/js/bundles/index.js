import React from 'react';
import Table from './Table.jsx';
import TableRow from './TableRow.jsx';
import ReactDOM from 'react-dom';

(function (win) {
    var self = {
        run(id) {
            $.ajax({
                url: '/profile/load/' + id,
                dataType: 'json',
                success: function (profile) {
                    var rows = [];

                    profile.rows.forEach(function (rowData) {
                        rows.push({
                            id: rowData.id,
                            type: rowData.type,
                            hash: 'hash-' + Math.random().toString(36).substr(2, 16),
                            label: rowData.label,
                            isRequired: false,
                            value: '',
                            defaultValue: []
                        });
                    });

                    ReactDOM.render(
                        <Table profile={profile} rows={rows} />,
                        document.getElementById('container')
                    );
                }
            });
        }
    };

    win.app = self;
}(window));