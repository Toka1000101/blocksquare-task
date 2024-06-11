import { FilterProps } from "../../types/Props"


const Filter:React.FC<FilterProps> = ({onclick}) => {


    return (
        <div>
            <button onClick={() => onclick(3)}>3x3</button>
            <button onClick={() => onclick(4)}>4x4</button>
            <button onClick={() => onclick(5)}>5x5</button>
            <button onClick={() => onclick(6)}>6x6</button>
            <button onClick={() => onclick(7)}>7x7</button>
            <button onClick={() => onclick(8)}>8x8</button>
        </div>
    )
}


export default Filter