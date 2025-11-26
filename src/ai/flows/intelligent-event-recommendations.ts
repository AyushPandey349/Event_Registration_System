// src/ai/flows/intelligent-event-recommendations.ts
'use server';

/**
 * @fileOverview This file defines a Genkit flow for providing intelligent event recommendations to users.
 *
 * The flow takes user profile information, past booking history, and preferences as input, and uses an LLM
 * to generate a list of personalized event recommendations.
 *
 * @param {IntelligentEventRecommendationsInput} input - The input data for generating event recommendations.
 * @returns {Promise<IntelligentEventRecommendationsOutput>} - A promise that resolves to the generated event recommendations.
 */

import {ai} from '@/ai/genkit';
import {z} from 'genkit';


const IntelligentEventRecommendationsInputSchema = z.object({
  userProfile: z.string().describe('User profile information, including demographics and interests.'),
  pastBookings: z.string().describe('History of events booked by the user.'),
  preferences: z.string().describe('User preferences for events, such as category, location, and date.'),
});

export type IntelligentEventRecommendationsInput = z.infer<typeof IntelligentEventRecommendationsInputSchema>;

const IntelligentEventRecommendationsOutputSchema = z.object({
  recommendations: z.string().describe('A list of event recommendations personalized for the user.'),
});

export type IntelligentEventRecommendationsOutput = z.infer<typeof IntelligentEventRecommendationsOutputSchema>;


export async function generateEventRecommendations(input: IntelligentEventRecommendationsInput): Promise<IntelligentEventRecommendationsOutput> {
  return intelligentEventRecommendationsFlow(input);
}

const prompt = ai.definePrompt({
  name: 'intelligentEventRecommendationsPrompt',
  input: {schema: IntelligentEventRecommendationsInputSchema},
  output: {schema: IntelligentEventRecommendationsOutputSchema},
  prompt: `You are an AI assistant that provides personalized event recommendations to users.
  Based on the user's profile, past bookings, and preferences, generate a list of events that the user might be interested in.

  User Profile: {{{userProfile}}}
  Past Bookings: {{{pastBookings}}}
  Preferences: {{{preferences}}}

  Recommendations:`,
});

const intelligentEventRecommendationsFlow = ai.defineFlow(
  {
    name: 'intelligentEventRecommendationsFlow',
    inputSchema: IntelligentEventRecommendationsInputSchema,
    outputSchema: IntelligentEventRecommendationsOutputSchema,
  },
  async input => {
    const {output} = await prompt(input);
    return output!;
  }
);
