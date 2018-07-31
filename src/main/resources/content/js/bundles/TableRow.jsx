import React from 'react';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import Col from 'react-bootstrap/lib/Col';
import Checkbox from 'react-bootstrap/lib/Checkbox';

const TYPE_TEXT = 1;
const TYPE_PHONE = 2;
const TYPE_COMBOBOX = 3;
const TYPE_CHECKBOX = 4;
const TYPE_TEXTAREA = 5;


export default class TableRow extends React.Component {

    static get TYPE_TEXT() {
        return TYPE_TEXT;
    }

    static get TYPE_PHONE() {
        return TYPE_PHONE;
    }

    static get TYPE_COMBOBOX() {
        return TYPE_COMBOBOX;
    }

    static get TYPE_CHECKBOX() {
        return TYPE_CHECKBOX;
    }

    static get TYPE_TEXTAREA() {
        return TYPE_TEXTAREA;
    }

    constructor(props) {
        super(props);

        this.state = props.data;
        this.state.values = [''];

        this.onChangeIsRequired = this.onChangeIsRequired.bind(this);
        this.onChangeType = this.onChangeType.bind(this);
        this.onChangeLabel = this.onChangeLabel.bind(this);
    }

    getTypes() {
        return new Map([
            [TableRow.TYPE_TEXT, 'Текст'],
            [TableRow.TYPE_PHONE, 'Телефон'],
            [TableRow.TYPE_COMBOBOX, 'Комбобокс'],
            [TableRow.TYPE_CHECKBOX, 'Чек-бокс'],
            [TableRow.TYPE_TEXTAREA, 'Абзац'],
        ]);
    }

    getData() {
        return {value: 1};
    }

    onChangeType(event) {
        this.setState({
            type: parseInt(event.target.value, 10),
            values: [this.state.values[0]]
        });
    }

    onChangeValue(event, i) {
        this.state.values[i] = event.target.value;

        if (this.state.type === TableRow.TYPE_COMBOBOX) {
            if (this.state.values[i] !== '' && this.state.values[i + 1] === undefined) {
                this.state.values.push('');
            } else if (this.state.values[i] === '' && this.state.values[i + 1] !== undefined && this.state.values[i + 1] === '') {
                this.state.values.pop();
            }
        }

        this.setState({values: this.state.values});
    }

    onChangeIsRequired(event) {
        let isRequired = false;

        if (event.target.checked) {
            isRequired = true;
        }

        this.setState({isRequired: isRequired});
    }

    onChangeLabel(event) {
        this.setState({label: event.target.value});
    }

    render() {
        let value, defaultValue;

        if (this.state.type === TableRow.TYPE_COMBOBOX) {
            defaultValue =
                <select className="form-control" name={'rows[' + this.props.number + '].values'}>
                    {this.state.values.filter((value) => value !== '').map((value, i) => <option value={i}>{value}</option>)}
                </select>;
            value = this.state.values.map((value, i) =>
                <input name={'rows[' + this.props.number + '].values'}onChange={event => this.onChangeValue(event, i)} style={{'margin-bottom': '5px'}} type="text" value={value} className="form-control"/>
            );
        } else if (this.state.type === TableRow.TYPE_CHECKBOX) {
            defaultValue = <input type="checkbox" name={'rows[' + this.props.number + '].default'} className="form-control"/>;
            value = <input disabled={true} name={'rows[' + this.props.number + '].value'} type="text" className="form-control"/>;
        } else {
            defaultValue = <input type="text" name={'rows[' + this.props.number + '].default'} className="form-control"/>;
            value = <input disabled={true} name={'rows[' + this.props.number + '].value'} type="text" className="form-control"/>;
        }

        return (
            <tr>
                <td>
                    <input type="hidden" value={this.state.id} name={'rows[' + this.props.number + '].id'} />
                    <FormGroup>
                        <Col sm={12}><Checkbox onClick={this.props.onCheckRow} /></Col>
                    </FormGroup>
                </td>
                <td>
                    <FormGroup>
                        <Col sm={12}>
                            <select value={this.state.type} name={'rows[' + this.props.number + '].type'} className="form-control" onChange={this.onChangeType}>
                                {Array.from(this.getTypes()).map(value => <option value={value[0]}>{value[1]}</option>)}
                            </select>
                        </Col>
                    </FormGroup>
                </td>
                <td>
                    <FormGroup>
                        <Col sm={12}><input type="text" name={'rows[' + this.props.number + '].label'} value={this.state.label} onChange={this.onChangeLabel} className="form-control"/></Col>
                    </FormGroup>
                </td>
                <td>
                    <FormGroup>
                        <Col sm={12}><Checkbox name={'rows[' + this.props.number + '].isRequired'} onChange={this.onChangeIsRequired} defaultChecked={this.state.isRequired} /></Col>
                    </FormGroup>
                </td>
                <td>
                    <FormGroup>
                        <Col sm={12}>{value}</Col>
                    </FormGroup>
                </td>
                <td>
                    <FormGroup>
                        <Col sm={12}>{defaultValue}</Col>
                    </FormGroup>
                </td>
            </tr>
        );
    };
}