import { Problem } from "../types/Api";
import { BinaryValue } from "../types/Types";

export const toBinaryValue = (num: number): BinaryValue => (num % 2) === 0 ? 0 : 1;
export const boolToBinaryValue = (b: boolean): BinaryValue => b ? 1 : 0;
export const boolArrayToBinValArray = (arr: boolean[]) => arr.map(x => boolToBinaryValue(x));
export const problemsToBinaryValue = (problems: Problem[]) => problems.map(x => boolArrayToBinValArray(x.description))
export const boolArrayTo2DNumberArray = (arr: boolean[]): number[][] => {
    const size = Math.ceil(Math.sqrt(arr.length));
  
    const result: number[][] = Array.from({ length: size }, () => Array(size).fill(0));
  
    for (let i = 0; i < arr.length; i++) {
      const row = Math.floor(i / size);
      const col = i % size;
      result[row][col] = arr[i] ? 1 : 0;
    }
  
    return result;
  };