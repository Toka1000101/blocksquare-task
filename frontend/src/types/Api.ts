
export interface Problem {
  id: number;
  description: boolean[];
  size: number;
}

export interface Solution {
  problemId: number;
  solutionMatrix: boolean[];
}

export interface ProblemErrorResponse {
  error: string
}

export interface ProblemResponse {
  solution: number[]
}

export interface ProblemPayload {
  matrix: number[][]
}



