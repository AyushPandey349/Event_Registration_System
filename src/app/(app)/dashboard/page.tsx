import { PageHeader } from '@/components/page-header';
import { Button } from '@/components/ui/button';
import Link from 'next/link';
import { ArrowRight, Plus } from 'lucide-react';
import { EventCard } from '@/components/event-card';
import { events } from '@/lib/placeholder-data';
import RecommendationsForm from './components/recommendations-form';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';


export default function DashboardPage() {
  const featuredEvents = events.slice(0, 3);

  return (
    <>
      <PageHeader
        title="Dashboard"
        description="Welcome back! Here's an overview of what's happening."
      >
        <Button asChild>
          <Link href="/events/create">
            <Plus className="mr-2 h-4 w-4" />
            Create Event
          </Link>
        </Button>
      </PageHeader>

      <div className="grid grid-cols-1 gap-8 lg:grid-cols-3">
        <div className="lg:col-span-2">
            <section>
                <div className="mb-4 flex items-center justify-between">
                    <h2 className="font-headline text-2xl font-bold">Featured Events</h2>
                    <Button variant="link" asChild>
                        <Link href="/events">
                        View All <ArrowRight className="ml-2 h-4 w-4" />
                        </Link>
                    </Button>
                </div>
                <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 xl:grid-cols-3">
                    {featuredEvents.map((event) => (
                    <EventCard key={event.id} event={event} />
                    ))}
                </div>
            </section>
        </div>
        
        <div className="lg:col-span-1">
            <section>
                <Card className="bg-card">
                    <CardHeader>
                        <CardTitle className="font-headline text-2xl">Event Recommendations</CardTitle>
                        <CardDescription>Get personalized suggestions powered by AI.</CardDescription>
                    </CardHeader>
                    <CardContent>
                        <RecommendationsForm />
                    </CardContent>
                </Card>
            </section>
        </div>
      </div>
    </>
  );
}
