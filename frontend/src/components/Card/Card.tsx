import { useNavigate } from "react-router-dom";
import { CardProps } from "../../types/Props";
import { boolArrayToBinValArray } from "../../utils/Helpers";
import Board from "../board/Board";
import "./Card.css"


const Card:React.FC<CardProps> = ({matrix, size, id}) => {

    const navigate = useNavigate();
    const description = boolArrayToBinValArray(matrix);
    const handleClick = () => {
        navigate("/editor", {state:{matrix: description, size, editPage: false,id: id}})
    }

    return (
        <div className="Card" onClick={handleClick}>
            <Board size={size} matrix={description} editPage={false} id={id}/>
        </div>
    );
}

export default Card;