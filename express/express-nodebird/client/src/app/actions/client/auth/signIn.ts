'use client';

import { ActionResponse } from '@/app/actions/action-response';

export const signIn = async (
  email: string,
  password: string,
): Promise<ActionResponse<void>> => {
  try {
    const url = process.env.NEXT_PUBLIC_SERVER_URL + '/auth/login';

    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'include',
      body: JSON.stringify({
        email,
        password,
      }),
    });

    const data = await response.json();

    if (data.error) {
      return {
        status: 'error',
        error: data.error,
      };
    }

    return {
      status: 'success',
      data,
    };
  } catch (err) {
    if (err instanceof Error) {
      return {
        status: 'error',
        error: {
          message: err.message,
        },
      };
    }

    return {
      status: 'error',
      error: {
        message: 'An error occurred',
      },
    };
  }
};
