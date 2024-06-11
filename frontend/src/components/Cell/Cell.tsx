import React from "react";
import {CellProps} from "../../types/Props";
import "./Cell.css"

const Cell: React.FC<CellProps> = ({rowIndex , colIndex, isOn, value, toggle, disabled, editMode, isSolution}) => 
    <button
        className={`${isOn ? "lightOn" : "lightOff"} 
            ${editMode ? "editMode" : ""} 
        ${isSolution ? "dot": ""}`}
        onClick={toggle}
        disabled={disabled}
    >
        ^
    </button>
export default Cell;
