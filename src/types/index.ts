export type Event = {
  id: string;
  title: string;
  description: string;
  date: string;
  time: string;
  location: string;
  category: 'Music' | 'Tech' | 'Food' | 'Art' | 'Sports' | 'Wellness';
  organizer: string;
  price: number;
  imageUrl: string;
  imageHint: string;
};

export type Booking = {
  id: string;
  eventTitle: string;
  eventDate: string;
  location: string;
  bookingDate: string;
  status: 'Confirmed' | 'Pending' | 'Cancelled';
};

export type User = {
  id: string;
  name: string;
  email: string;
  role: 'User' | 'Organizer' | 'Admin';
  joinedDate: string;
};
