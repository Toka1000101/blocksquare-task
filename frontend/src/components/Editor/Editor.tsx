import { useState } from "react";
import Board from "../board/Board";
import { BinaryValue } from "../../types/Types";
import { useLocation } from "react-router-dom";

const Editor:React.FC = () => {

    const location = useLocation();


    const initValues = (): BinaryValue[] => {
        const arr = new Array(9).fill(0);
        return arr.map(x => x as BinaryValue)
    }

    const { matrix, size, editMode, id } = location.state || {matrix:initValues(), size: 3, editMode: true, id: null };

    return (
        <div className="editor">
            {
            editMode ?
            <Board size={3} matrix={initValues()} editPage={true} id={null}/> :
            <Board size={size} matrix={matrix} editPage={true} id={id}/>
            }
        </div>
    )
}

export default Editor;