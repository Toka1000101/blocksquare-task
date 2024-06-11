import { BinaryValue } from "./Types";

export interface CellProps {
    rowIndex: number;
    colIndex: number;
    isOn: boolean;
    value: BinaryValue;
    toggle: any;
    disabled: boolean;
    editMode: boolean;
    isSolution: boolean;
}

export interface BoardProps {
    matrix: BinaryValue[];
    size: number;
    editPage: boolean;
    id: number | null;
}

export interface CardProps {
    matrix: boolean[];
    size: number;
    id: number;
}

export interface FilterProps {
    onclick: any;
}