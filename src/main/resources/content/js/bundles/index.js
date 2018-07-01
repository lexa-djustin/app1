import React from 'react';
import Table from './Table.jsx';
import TableRow from './TableRow.jsx';
import ReactDOM from 'react-dom';

const rows = [
    {
        //id: this.makeUniqueId(),
        type: TableRow.TYPE_TEXT
    }, {
        //id: this.makeUniqueId(),
        type: TableRow.TYPE_CHECKBOX
    }, {
        //id: this.makeUniqueId(),
        type: TableRow.TYPE_TEXT
    },
    {
        //id: this.makeUniqueId(),
        type: TableRow.TYPE_TEXT
    }, {
        //id: this.makeUniqueId(),
        type: TableRow.TYPE_TEXT
    }
];

class Row {
    constructor(type, label, isRequired, value, defaultValue) {
        this.type = type;
        this.hash = 'hash-' + Math.random().toString(36).substr(2, 16);
        this.label = label;
        this.isRequired = isRequired;
        this.value = value;
        this.defaultValue = defaultValue;
    }
}


var row1 = new Row(TableRow.TYPE_TEXT, 'Ваше имя', true, null, []);
var row2 = new Row(TableRow.TYPE_TEXT, 'Ваш номер телефона', true, null, []);
var row3 = new Row(TableRow.TYPE_TEXTAREA, 'Ваше адрес', false, null, []);

ReactDOM.render(
    <Table rows={[row1, row2, row3]} />,
    document.getElementById('container')
);
