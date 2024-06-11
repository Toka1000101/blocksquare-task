import React, { useState, useEffect } from "react";
import Cell from "../Cell/Cell";
import { boolArrayTo2DNumberArray, toBinaryValue } from "../../utils/Helpers";
import { BoardProps } from "../../types/Props";
import { BinaryValue } from "../../types/Types";
import Filter from "../Filter/Filter";
import { getProblemById, getSolutionByProblemId, postProblem } from "../../api/problems";
import { ProblemErrorResponse, ProblemPayload, ProblemResponse } from "../../types/Api";

const Board: React.FC<BoardProps> = ({ matrix, size, editPage, id }) => {


    const [board, setBoard] = useState<{ 
        values: number[][],
        editMode: boolean,
        nSize: number,
        solution:number[],
        noSolution: boolean
    }>
    ({
        values: [],
        editMode: false,
        nSize: size,
        solution: [],
        noSolution: false
    });

    const createBinaryMatrix = (matrix: BinaryValue[], size: number): number[][] => {
        const arr: number[][] = [];
        for (let i = 0; i < size; i++) {
            const row: number[] = matrix.slice(i * size, (i + 1) * size);
            arr.push(row);
        }
        return arr;
    };

    useEffect(() => {
        setBoard((prevBoard) => ({
            ...prevBoard,
            noSolution: false,
            values: createBinaryMatrix(matrix, size),
            size: size
        }));
    }, [matrix, size]);

    const toggle = (row: number, col: number, editMode: boolean) => {
        setBoard((prevBoard) => {
            const updatedBoard = prevBoard.values.map(row => [...row]);

            const toggleCell = (r: number, c: number) => {
                if (r >= 0 && r < prevBoard.nSize && c >= 0 && c < prevBoard.nSize) {
                    updatedBoard[r][c] = updatedBoard[r][c] === 1 ? 0 : 1;
                }
            };

            toggleCell(row, col);
            const nextSol = board.solution.map(x => x);
            nextSol[row * board.nSize + col] = 0;
            setBoard((prevBoard) => ({
                ...prevBoard,
                noSolution: false,
                solution: nextSol
            }));

            if (!editMode) {
                toggleCell(row - 1, col);
                toggleCell(row + 1, col);
                toggleCell(row, col - 1);
                toggleCell(row, col + 1);
            }

            return {
                ...prevBoard,
                values: updatedBoard
            };
        });
    };

    const toggleEditMode = () => {
        setBoard((prevBoard) => ({
            ...board,
            noSolution: false,
            editMode: !prevBoard.editMode
        }));
    };

    const changeGrid = (newSize: number) => {
        const newGrid = Array.from({ length: newSize }, () => new Array(newSize).fill(0));
        setBoard({
            noSolution: false,
            nSize: newSize,
            values: newGrid,
            editMode: true,
            solution: []
        });
    };

    const handleSubmit = async (values: number[][]) => {

        if(id != null) {
            console.log(id)
            const solution = await getSolutionByProblemId(id);
            const solMatrix = solution.solutionMatrix.map(x => x ? 1 : 0);

            setBoard((prevBoard) => ({
                ...prevBoard,
                solution: solMatrix,
                editMode: false
            }));

            return;
        }

        const data:ProblemPayload = {
            matrix: values
        } 
        postProblem(data)
        .then(response => {
          if ('error' in response) {
            console.error('Problem error response:', response.error);
          } else {
            console.log('Problem created successfully:', response);
            const problemData = response; 

            setBoard((prevBoard) => ({
                ...prevBoard,
                editMode: false,
                solution: problemData.solution
            }));
          }
        })
        .catch(err => {
          if (err instanceof Error) {
            console.error('Error in processing:', err.message);
          } else {

            setBoard((prevBoard) => ({
                ...prevBoard,
                noSolution: true
            }));
            console.error('Error response:', err as ProblemErrorResponse);
          }
        });

    }

    const reset = async () => {
        if(id == null) {
            resetEditPage();
        }else{
            try {
            const fetchedProblem = await getProblemById(id);
            const vals = boolArrayTo2DNumberArray(fetchedProblem.description);
            setBoard((prevBoard) => ({
                ...prevBoard,
                values: vals,
                solution: []
            }));
            } catch (err) {
                console.error(err);
            }
        }
    }

    const resetEditPage = ()  => {
        const newGrid: number[][] = Array.from({ length: board.nSize }, () => new Array(board.nSize).fill(0));
        const newSolution: number[] = Array.from({length: board.nSize}, () => 0);
        setBoard({
            ...board,
            values: newGrid,
            solution: newSolution,
            noSolution: false
        });
    }

    return (
        <div className="board">
            <div>
                {editPage && (
                    <>
                        <Filter onclick={changeGrid} />
                    </>
                )}
            </div>
            {board.values.map((row, rowIndex) =>
                <div className="row" key={rowIndex}>
                    {row.map((_, colIndex) =>
                        <Cell
                            isOn={board.values[rowIndex][colIndex] === 1}
                            rowIndex={rowIndex}
                            colIndex={colIndex}
                            key={`${rowIndex}-${colIndex}`}
                            value={toBinaryValue(board.values[rowIndex][colIndex])}
                            toggle={() => toggle(rowIndex, colIndex, board.editMode)}
                            disabled={false}
                            editMode={editPage}
                            isSolution={board.solution[rowIndex * board.nSize + colIndex] === 1}
                        />
                    )}
                </div>
            )}
            <div>
                {editPage && (
                    <>
                        <button onClick={toggleEditMode}>{board.editMode ? "Play" : "Edit"}</button>
                        <button onClick={() => handleSubmit(board.values)}>Get Solution</button>
                        <button onClick={() => reset()}>Reset</button>
                    </>
                )}
                {
                    board.noSolution && 
                    <div>No Solution</div>
                }
            </div>
        </div>
    );
};

export default Board;