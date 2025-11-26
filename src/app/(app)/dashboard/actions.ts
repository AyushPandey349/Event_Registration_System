'use server';

import { generateEventRecommendations } from '@/ai/flows/intelligent-event-recommendations';
import { z } from 'zod';

const formSchema = z.object({
  preferences: z.string().min(10),
});

export async function getRecommendations(values: z.infer<typeof formSchema>) {
  // In a real app, you'd fetch this data for the logged-in user
  const userProfile = "28-year-old software developer living in San Francisco, enjoys live music, technology conferences, and outdoor activities.";
  const pastBookings = "Attended 'Indie Music Fest' last summer, booked tickets for 'AI in Tech Summit 2023'.";

  try {
    const result = await generateEventRecommendations({
      userProfile,
      pastBookings,
      preferences: values.preferences,
    });
    return { success: true, data: result.recommendations };
  } catch (error) {
    console.error('AI recommendation error:', error);
    return { success: false, error: 'Failed to generate recommendations. Please try again later.' };
  }
}
