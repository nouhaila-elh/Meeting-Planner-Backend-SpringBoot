package room.reservation.entities;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data   // c'est équivalent à ajouter les annotations @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode à la classe
@Entity
@Table(name = "meeting")
public class Meeting {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	// id of the meeting
	
	@Column(name = "type", unique = true)
	private String type;   // the type of the meeting
	
	@ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      })
	@JoinTable(name = "meeting_equipments",joinColumns = { @JoinColumn(name = "meeting_id") },inverseJoinColumns = { @JoinColumn(name = "equipment_id") })
	private Set<Equipment> equipments = new HashSet<>();
	
    
    @OneToMany(mappedBy = "meeting", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Request> requests;
    
    public void addRequest(Request request){
    	requests.add(request);
    	request.setMeeting(this);
    }
}
