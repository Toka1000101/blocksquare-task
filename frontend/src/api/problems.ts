import axios, { AxiosRequestConfig, AxiosResponse } from 'axios';
import { Problem, ProblemErrorResponse, ProblemPayload, ProblemResponse, Solution } from '../types/Api';

const url = 'http://localhost:8080';

const api = axios.create({
  baseURL: url,
  timeout: 5000,
  headers: {'Content-Type': 'application/json'},
});

export const fetchProblems = async (): Promise<Problem[]> => {
  try {
    const response: AxiosResponse<Problem[]> = await api.get('/problems');
    return response.data;
  } catch (error) {
    console.error('Error fetching problems:', error);
    throw error;
  }
};

export const getProblemById = async (id: number): Promise<Problem> => {
  try{
    const response: AxiosResponse<Problem> = await api.get(`problems/${id}`)
    return response.data;
  }catch(error){
    console.error(`Error fetching problem with id ${id}:`, error);
    throw error;
  }
}

export const getSolutionByProblemId = async (id: number): Promise<Solution> => {
  try{
    const response: AxiosResponse<Solution> = await api.get(`solutions/problem/${id}`)
    return response.data;
  }catch(error){
    console.error(`Error fetching solution with problemd ${id}:`, error);
    throw error;
  }
}



const config: AxiosRequestConfig = {
  headers: {
    'Content-Type': 'application/json'
  }
}

export const postProblem = async (payload: ProblemPayload): Promise<ProblemResponse|ProblemErrorResponse> => {
  try{
    const uri = url + "/problems";
    const response: AxiosResponse<ProblemResponse | ProblemErrorResponse> = await axios.post(uri, payload, config);

    if(response.status === 201) {
      console.log(response.data)
      console.log('Successfully created.' + response.data);
      return response.data as ProblemResponse;
    }else if(response.status === 500) {
      console.error("Error", response.data)
      return response.data as ProblemErrorResponse;
    }

    console.log('Unexpected response code:', response.status, response.data);
    return response.data;
  }catch(error) {
    if (axios.isAxiosError(error)) {
      console.error('Axios error:', error.message);
      if (error.response) {
        console.error('Error response data:', error.response.data);
        throw error.response.data as ProblemErrorResponse;
      }
    } else {
      console.error('Unexpected error:', error);
    }
  }
  throw new Error('Unexpected error occurred'); 
}



