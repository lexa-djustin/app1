import React from 'react';
import Axios from 'axios';
import TableRow from './TableRow';
import PropTypes from 'prop-types';

export default class Table extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            checked: new Set(),
            rows: props.rows
        };
    };

    onMoveRowUp () {
        let self = this,
            rows = this.state.rows.slice();

        this.state.rows.forEach(function (row, i) {
            if (self.state.checked.has(row.hash) && rows[i - 1] !== undefined && !self.state.checked.has(rows[i - 1].hash)) {
                [rows[i - 1], rows[i]] = [rows[i], rows[i - 1]];

                return false;
            }
        });

        self.setState({rows: rows});
    };

    onMoveRowDown () {
        let self = this,
            rows = this.state.rows.slice().reverse();

        this.state.rows.slice().reverse().forEach(function (row, i) {
            if (self.state.checked.has(row.hash) && rows[i - 1] !== undefined && !self.state.checked.has(rows[i - 1].hash)) {
                [rows[i - 1], rows[i]] = [rows[i], rows[i - 1]];

                return false;
            }
        });

        self.setState({rows: rows.reverse()});
    };

    onCheckRow (event, hash) {
        this.state.rows.forEach(function(row){
            console.log(row.isRequired);
        });

        if (event.target.checked) {
            this.state.checked.add(hash);
        } else {
            this.state.checked.delete(hash);
        }
    };

    onDeleteRow() {
        var rows = [],
            self = this;

        this.state.rows.forEach(function (row) {
            if (self.state.checked.has(row.hash)) {
                self.state.checked.delete(row.hash);
                return true;
            }

            rows.push(row);
        });

        this.setState({
            rows: rows
        });
    };

    onAddRow() {
        this.state.rows.push({
            hash: this.makeUniqueId(),
            type: TableRow.TYPE_TEXT
        });

        this.setState({rows: this.state.rows});
    }

    onSubmit(event) {
        event.preventDefault();

        let formData = new FormData(event.target);

        formData.forEach(function (value, name) {
            //console.log(name, value);
        });

        /*
        Axios({
            method: 'post',
            url: '/profile/',
            data: formData,
            config: { headers: {'Content-Type': 'multipart/form-data' }}
        }).then(function (response) {
            //console.log(response);
        });
        */

        $.ajax({
            type: 'POST',
            data: $(event.target).serialize(),
            url: '/profile/'
        });
    }

    render() {
        return (
            <form onSubmit={this.onSubmit.bind(this)}>
                <table id="table" className="table table-bordered">
                    <thead>
                    <tr>
                        <th colSpan={6}>
                            <div className="row">
                                <div className="form-group head-inputs-1 col-xs-6">
                                    <label htmlFor="" className="col-sm-2 control-label">Название</label>
                                    <div className="col-sm-10">
                                        <input type="email" className="form-control"/>
                                    </div>
                                </div>
                                <div className="form-group head-inputs-2 col-xs-6">
                                    <button type="button" className="btn pull-right">Просмотр</button>
                                    <button type="submit" className="btn pull-right">Сохранить</button>
                                    <button type="button" className="btn pull-right">Отменить</button>
                                </div>
                            </div>

                            <div className="form-group head-inputs-3 col-xs-6">
                                <button onClick={this.onAddRow.bind(this)} type="button" className="btn">Добавить поле</button>
                                <button onClick={this.onDeleteRow.bind(this)} type="button" className="btn">Удалить поле</button>
                                <button onClick={this.onMoveRowUp.bind(this)} type="button" id="uo" className="btn">Вверх</button>
                                <button onClick={this.onMoveRowDown.bind(this)} type="button" id="down" className="btn">Вниз</button>
                            </div>
                        </th>
                    </tr>
                    <tr>
                        <th className="column1"></th>
                        <th className="column2">Тип поля</th>
                        <th className="column3">Лэйбл</th>
                        <th className="column4">*</th>
                        <th className="column5">Значения</th>
                        <th className="column6">По умолчанию</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.rows.map((row, i) =>
                        <TableRow onCheckRow={(event) => this.onCheckRow(event, row.hash)} key={row.hash} number={i} data={row}/>
                    )}
                    </tbody>
                </table>
            </form>
        );
    };
}